package controller;

import edu.ufl.digitalworlds.j4k.Skeleton;

/*
 Muhammad Naim bin Mohmad Shofi
 Universiti Putra Malaysia
 naim_shofi@yahoo.com

 This program is for Final Year Project for Bachelor of Computer Science and Information Technology.
 The title of this project is RoboShop : Shopping Robot Assistant Using Lego Mindstorm and Tetrix.
 It used J4K Library and LeJOS SDK.

 In this class, the Kinect will detect gesture to controlled the robot arm.
 */

public class ArmSegment {

	Skeleton skeleton;

	public ArmSegment(Skeleton skeleton) {
		this.skeleton = skeleton;
	}

	public int getMoveId() {
		int id = 0;

		if (armUp()) {
			id = 5;
		} else if (armDown()) {
			id = 6;
		} else if (armRight()) {
			id = 7;
		} else if (armLeft()) {
			id = 8;
		}

		return id;
	}

	public boolean armUp() {
		if ((skeleton.get3DJointY(Skeleton.HAND_LEFT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_LEFT) * 10) > 2
				&& (skeleton.get3DJointY(Skeleton.HAND_LEFT) * 10 - skeleton
						.get3DJointY(Skeleton.SHOULDER_LEFT) * 10) < 4) {
			return true;
		}
		return false;
	}

	public boolean armDown() {
		if ((skeleton.get3DJointY(Skeleton.HAND_LEFT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_LEFT) * 10) < -1
				&& (skeleton.get3DJointY(Skeleton.HAND_LEFT) * 10 - skeleton
						.get3DJointY(Skeleton.SHOULDER_LEFT) * 10) > -4) {
			return true;
		}
		return false;
	}

	public boolean armLeft() {
		if ((skeleton.get3DJointX(Skeleton.HAND_LEFT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_LEFT) * 10) > -6
				&& (skeleton.get3DJointX(Skeleton.HAND_LEFT) * 10 - skeleton
						.get3DJointX(Skeleton.SHOULDER_LEFT) * 10) < -2) {
			return true;
		}
		return false;
	}

	public boolean armRight() {
		if ((skeleton.get3DJointX(Skeleton.HAND_LEFT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_LEFT) * 10) > 0
				&& (skeleton.get3DJointX(Skeleton.HAND_LEFT) * 10 - skeleton
						.get3DJointX(Skeleton.SHOULDER_LEFT) * 10) < 4) {
			return true;
		}
		return false;
	}
}
