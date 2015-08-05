package controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import lejos.pc.comm.NXTConnector;
import edu.ufl.digitalworlds.j4k.DepthMap;
import edu.ufl.digitalworlds.j4k.J4KSDK;
import edu.ufl.digitalworlds.j4k.Skeleton;

/*
Muhammad Naim bin Mohmad Shofi
Universiti Putra Malaysia
naim_shofi@yahoo.com

This program is for Final Year Project for Bachelor of Computer Science and Information Technology.
The title of this project is RoboShop : Shopping Robot Assistant Using Lego Mindstorm and Tetrix.
It used J4K Library and LeJOS SDK.

In this class, the program will process the input from the Kinect sensor and sent the Movement ID to the robot controller.
*/
public class KinectRB extends J4KSDK {

	VideoPanelRB viewer = null;
	JLabel user_icon = null;
	SkeletonProcess skel_process = null;
	NXTConnector conn = null;
	OutputStream dos;
	boolean userReady;
	int userIDTracked;
	int frameCount = 0;
	JTextArea console;
	int curMovement = 0;

	public KinectRB() {
		skel_process = new SkeletonProcess();
		conn = new NXTConnector();
	}

	public void setNXTconnection(NXTConnector conn) {
		this.conn = conn;
		dos = conn.getOutputStream();
	}

	public void setViewer(VideoPanelRB viewer) {
		this.viewer = viewer;
	}

	public VideoPanelRB getViewer() {
		return viewer;
	}

	public void setConsole(JTextArea console) {
		this.console = console;
	}

	public void setUserStat(JLabel user_icon) {
		this.user_icon = user_icon;
	}

	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	@Override
	public void onDepthFrameEvent(short[] depth_frame, byte[] body_index,
			float[] xyz, float[] uv) {

		if (viewer == null) {
			return;
		}

		DepthMap map = new DepthMap(getDepthWidth(), getDepthHeight(), xyz);

		map.setMaximumAllowedDeltaZ(0.5);

		if (uv != null)
			map.setUV(uv);

		viewer.map = map;
	}

	@Override
	public void onSkeletonFrameEvent(boolean[] skeleton_tracked,
			float[] joint_position, float[] joint_orientation,
			byte[] joint_status) {

		if (viewer == null || viewer.skeletons == null) {
			userReady = false;
			return;
		}

		for (int i = 0; i < getSkeletonCountLimit(); i++) {
			viewer.skeletons[i] = Skeleton.getSkeleton(i, skeleton_tracked,
					joint_position, joint_orientation, joint_status, this);
			if (viewer.skeletons[i].isTracked()) {
				userIDTracked = viewer.skeletons[i].getPlayerID();
			}
		}

		skel_process.setSkeleton(viewer.skeletons[userIDTracked]);
		//System.out.println("Left Hand : " + skel_process.getSkeleton().get3DJointX(Skeleton.HAND_LEFT)*10);
		//System.out.println("Left Shoulder : " + skel_process.getSkeleton().get3DJointX(Skeleton.SHOULDER_LEFT)*10);
		if (userReady) {
			ImageIcon icon = createImageIcon("images/icon_yes_user.png",
					"User Detected!");
			user_icon.setIcon(icon);
			if (curMovement == skel_process.getToMovement()) {

			} else {
				curMovement = skel_process.getToMovement();
				console.append("Movement ID : " + curMovement + "\n");
				try {
					dos.write(skel_process.getToMovement());
					dos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			// wait for wave
			userReady = skel_process.getWaveCompleted();
			System.out.println("User Not Ready");
		}
	}

	@Override
	public void onColorFrameEvent(byte[] color_frame) {
		if (viewer == null || viewer.videoTexture == null)
			return;
		viewer.videoTexture.update(getColorWidth(), getColorHeight(),
				color_frame);
	}

}
