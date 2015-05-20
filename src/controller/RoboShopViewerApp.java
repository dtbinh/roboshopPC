package controller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lejos.pc.comm.NXTConnector;
import edu.ufl.digitalworlds.gui.DWApp;

/*
 * Copyright 2011-2014, Digital Worlds Institute, University of 
 * Florida, Angelos Barmpoutis.
 * All rights reserved.
 *
 * When this program is used for academic or resea	rch purposes, 
 * please cite the following article that introduced this Java library: 
 * 
 * A. Barmpoutis. "Tensor Body: Real-time Reconstruction of the Human Body 
 * and Avatar Synthesis from RGB-D', IEEE Transactions on Cybernetics, 
 * October 2013, Vol. 43(5), Pages: 1347-1356. 
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *     * Redistributions of source code must retain this copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce this
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
@SuppressWarnings("serial")
public class RoboShopViewerApp extends DWApp implements ChangeListener {

	KinectRB myKinect;
	VideoPanelRB main_panel;
	JTextArea console;
	JSlider elevation_angle;
	JLabel user_stat;
	
	JButton reconnect;

	public void GUIsetup(JPanel p_root) {
		/**
		 * if(System.getProperty("os.arch").toLowerCase().indexOf("64")<0) {
		 * if(DWApp.showConfirmDialog("Performance Warning", "<html><center><br>
		 * WARNING: You are running a 32bit version of Java.<br>
		 * This may reduce significantly the performance of this application.<br>
		 * It is strongly adviced to exit this program and install a 64bit
		 * version of Java.<br>
		 * <br>
		 * Do you want to exit now?</center>")) System.exit(0); }
		 */
		setLoadingProgress("Intitializing KinectRB...", 20);
		myKinect = new KinectRB();

		if (!myKinect.start(KinectRB.DEPTH | KinectRB.COLOR | KinectRB.SKELETON
				| KinectRB.XYZ | KinectRB.PLAYER_INDEX)) {
			DWApp.showErrorDialog(
					"ERROR",
					"<html><center><br>ERROR: The KinectRB device could not be initialized.<br><br>1. Check if the Microsoft's KinectRB SDK was succesfully installed on this computer.<br> 2. Check if the KinectRB is plugged into a power outlet.<br>3. Check if the KinectRB is connected to a USB port of this computer.</center>");
			// System.exit(0);
		}


		setLoadingProgress("Intitializing OpenGL...", 60);
		main_panel = new VideoPanelRB();		
		
		console = new JTextArea();

		elevation_angle = new JSlider();
		elevation_angle.setMinimum(-27);
		elevation_angle.setMaximum(27);
		elevation_angle.setValue((int) myKinect.getElevationAngle());
		elevation_angle.setToolTipText("Elevation Angle ("
				+ elevation_angle.getValue() + " degrees)");
		elevation_angle.addChangeListener(this);

		JScrollPane areaScrollPane = new JScrollPane(console);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel setting_panel = new JPanel(new FlowLayout());
		reconnect = new JButton("Reconnect");
		reconnect.addActionListener(new reconnectListener());
		
		user_stat = new JLabel("No User");
		setting_panel.add(user_stat);
		setting_panel.add(new JLabel("Camera Elevation"));
		setting_panel.add(elevation_angle);
		setting_panel.add(areaScrollPane);
		setting_panel.add(reconnect);

		myKinect.setViewer(main_panel);
		myKinect.setUserStat(user_stat);
		myKinect.setSeatedSkeletonTracking(true);
		myKinect.computeUV(true);
		p_root.add(main_panel, BorderLayout.CENTER);
		p_root.add(setting_panel, BorderLayout.SOUTH);
	}

	public void GUIclosing() {
		myKinect.stop();
	}

	public static void main(String args[]) {

		createMainFrame("RoboShop Viewer And Controller");
		app = new RoboShopViewerApp();
		setFrameSize(730, 570, null);
	}
	
	//Start NXT connection
	public void connect() {

		NXTConnector conn = new NXTConnector();// create a new NXT connector
		boolean connected = conn.connectTo("btspp://00165317B710"); //try to connect any NXT over bluetooth
		//boolean connected = conn.connectTo("usb://");
		if (!connected) {// failure
			System.out.println("Failed");
			console.append("Failed to connect to any NXT\n");
			console.append("Press Reconect to retry.\n");
		}

		else// success!
		{
			System.out.println("Success");
			myKinect.setNXTconnection(conn);
			console.append("Connected to " + conn.getNXTInfo() + "\n");
		}

	}
	
	class reconnectListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			connect();// call main connect method to try to connect again
		}
	}

	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == elevation_angle) {
			if (!elevation_angle.getValueIsAdjusting()) {
				myKinect.setElevationAngle(elevation_angle.getValue());
				elevation_angle.setToolTipText("Elevation Angle ("
						+ elevation_angle.getValue() + " degrees)");
				console.append("\nKinect elevation changed : "
						+ myKinect.getElevationAngle());
			}
		}
	}

}
