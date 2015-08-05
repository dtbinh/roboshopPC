package controller;

import javax.media.opengl.GL2;

import edu.ufl.digitalworlds.j4k.DepthMap;
import edu.ufl.digitalworlds.j4k.Skeleton;
import edu.ufl.digitalworlds.j4k.VideoFrame;
import edu.ufl.digitalworlds.opengl.OpenGLPanel;

/*
 Muhammad Naim bin Mohmad Shofi
 Universiti Putra Malaysia
 naim_shofi@yahoo.com

 This program is for Final Year Project for Bachelor of Computer Science and Information Technology.
 The title of this project is RoboShop : Shopping Robot Assistant Using Lego Mindstorm and Tetrix.
 It used J4K Library and LeJOS SDK.
 
 In this class, the program will draw the video output and the skeleton to the canvas viewer.
 */
@SuppressWarnings("serial")
public class VideoPanelRB extends OpenGLPanel {

	private float view_rotx = 0.0f, view_roty = 0.0f, view_rotz = 0.0f;
	VideoFrame videoTexture;
	// Skeleton skeletons[];
	Skeleton skeletons[];
	DepthMap map = null;

	public void setup() {

		// OPENGL SPECIFIC INITIALIZATION (OPTIONAL)
		GL2 gl = getGL2();
		gl.glEnable(GL2.GL_CULL_FACE);
		float light_model_ambient[] = { 0.3f, 0.3f, 0.3f, 1.0f };
		float light0_diffuse[] = { 0.9f, 0.9f, 0.9f, 0.9f };
		float light0_direction[] = { 0.0f, -0.4f, 1.0f, 0.0f };
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glShadeModel(GL2.GL_SMOOTH);

		gl.glLightModeli(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, GL2.GL_FALSE);
		gl.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, GL2.GL_FALSE);
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, light_model_ambient, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light0_diffuse, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light0_direction, 0);
		gl.glEnable(GL2.GL_LIGHT0);

		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glColor3f(0.9f, 0.9f, 0.9f);

		skeletons = new Skeleton[6];
		videoTexture = new VideoFrame();

		background(0, 0, 0);
	}

	public void draw() {

		GL2 gl = getGL2();

		pushMatrix();

		translate(0, 0, -2);
		rotate(view_rotx, 1.0, 0.0, 0.0);
		rotate(view_roty, 0.0, 1.0, 0.0);
		rotate(view_rotz, 0.0, 0.0, 1.0);
		translate(0, 0, 2);

		if (map != null) {
			gl.glDisable(GL2.GL_LIGHTING);
			gl.glEnable(GL2.GL_TEXTURE_2D);
			gl.glColor3f(1f, 1f, 1f);
			videoTexture.use(gl);
			map.drawTexture(gl);
			gl.glDisable(GL2.GL_TEXTURE_2D);
		}

		gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);

		gl.glDisable(GL2.GL_LIGHTING);
		gl.glLineWidth(2);
		gl.glColor3f(1f, 0f, 0f);
		for (int i = 0; i < skeletons.length; i++) {
			if (skeletons[i] != null) {
				if (skeletons[i].getTimesDrawn() <= 10
						&& skeletons[i].isTracked()) {
					skeletons[i].draw(gl);
					skeletons[i].increaseTimesDrawn();
				}
			}
		}

		popMatrix();
	}
}
