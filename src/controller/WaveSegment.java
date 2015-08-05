package controller;

import edu.ufl.digitalworlds.j4k.Skeleton;

/*
 Muhammad Naim bin Mohmad Shofi
 Universiti Putra Malaysia
 naim_shofi@yahoo.com

 This program is for Final Year Project for Bachelor of Computer Science and Information Technology.
 The title of this project is RoboShop : Shopping Robot Assistant Using Lego Mindstorm and Tetrix.
 It used J4K Library and LeJOS SDK.

 In this class, the Kinect will detect gesture to start the movement tracking.
 */

public class WaveSegment {

	public WaveSegment() {

	}

	public boolean wavePartLeft(Skeleton skeleton) {
		if (skeleton.get3DJointY(Skeleton.HAND_RIGHT) > skeleton
				.get3DJointY(Skeleton.ELBOW_RIGHT)) {
			if (skeleton.get3DJointX(Skeleton.HAND_RIGHT) < skeleton
					.get3DJointX(Skeleton.ELBOW_RIGHT)) {
				return true;
			}
		}
		return false;
	}

	public boolean wavePartRight(Skeleton skeleton) {
		if (skeleton.get3DJointY(Skeleton.HAND_RIGHT) > skeleton
				.get3DJointY(Skeleton.ELBOW_RIGHT)) {
			if (skeleton.get3DJointX(Skeleton.HAND_RIGHT) > skeleton
					.get3DJointX(Skeleton.ELBOW_RIGHT)) {
				return true;
			}
		}
		return false;
	}

}
