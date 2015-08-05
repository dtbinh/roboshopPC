package controller;

import edu.ufl.digitalworlds.j4k.Skeleton;

/*
 Muhammad Naim bin Mohmad Shofi
 Universiti Putra Malaysia
 naim_shofi@yahoo.com

 This program is for Final Year Project for Bachelor of Computer Science and Information Technology.
 The title of this project is RoboShop : Shopping Robot Assistant Using Lego Mindstorm and Tetrix.
 It used J4K Library and LeJOS SDK.

 In this class, the Kinect will detect gesture to controlled the robot navigation movement.
 */

public class MoveSegment {

	Skeleton skeleton;

	public MoveSegment(Skeleton skeleton) {
		this.skeleton = skeleton;
	}

	public int getMoveId() {
		int id = 0;

		if (moveForward()) {
			id = 1;
		} else if (moveBackward()) {
			id = 2;
		} else if (rotateRight()) {
			id = 3;
		} else if (rotateLeft()) {
			id = 4;
		}

		return id;
	}

	public boolean moveForward() {
		if ((skeleton.get3DJointY(Skeleton.HAND_RIGHT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) > 2
				&& (skeleton.get3DJointY(Skeleton.HAND_RIGHT) * 10 - skeleton
						.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) < 6) {
			return true;
		}
		return false;
	}

	public boolean moveBackward() {
		if ((skeleton.get3DJointY(Skeleton.HAND_RIGHT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) < -1
				&& (skeleton.get3DJointY(Skeleton.HAND_RIGHT) * 10 - skeleton
						.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) > -6) {
			return true;
		}
		return false;
	}

	public boolean rotateRight() {
		if ((skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) > 3
				&& (skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10 - skeleton
						.get3DJointX(Skeleton.SHOULDER_RIGHT) * 10) < 6) {
			return true;
		}
		return false;
	}

	public boolean rotateLeft() {
		if ((skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) < 0
				&& (skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10 - skeleton
						.get3DJointX(Skeleton.SHOULDER_RIGHT) * 10) > -4) {
			return true;
		}
		return false;
	}
}
