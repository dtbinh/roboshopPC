package controller;

import edu.ufl.digitalworlds.j4k.Skeleton;

/*
Muhammad Naim bin Mohmad Shofi
Universiti Putra Malaysia
naim_shofi@yahoo.com

This program is for Final Year Project for Bachelor of Computer Science and Information Technology.
The title of this project is RoboShop : Shopping Robot Assistant Using Lego Mindstorm and Tetrix.
It used J4K Library and LeJOS SDK.

In this class, the Kinect will detect gesture to controlled the robot arm gripper.
*/

public class GripSegment {

	Skeleton skeleton;

	public GripSegment(Skeleton skeleton) {
		this.skeleton = skeleton;
	}

	public int getMoveId() {
		int id = 0;
		if (open()) {
			id = 9;
		} else if (close()) {
			id = 10;
		}
		return id;
	}

	public boolean open() {
		return openRight() && openLeft();
	}

	public boolean openRight() {
		if ((skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10
				- skeleton.get3DJointX(Skeleton.SHOULDER_RIGHT) * 10 > 1)
				&& skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10
						- skeleton.get3DJointX(Skeleton.SHOULDER_RIGHT) * 10 < 3) {
			return true;
		}
		return false;
	}

	public boolean openLeft() {
		if ((skeleton.get3DJointX(Skeleton.SHOULDER_LEFT) * 10
				- skeleton.get3DJointX(Skeleton.HAND_LEFT) * 10 > 1)
				&& skeleton.get3DJointX(Skeleton.SHOULDER_LEFT) * 10
						- skeleton.get3DJointX(Skeleton.HAND_LEFT) * 10 < 3) {
			return true;
		}
		return false;
	}

	public boolean close() {
		return closeRight() && closeLeft();
	}

	public boolean closeRight() {
		if ((skeleton.get3DJointX(Skeleton.SHOULDER_RIGHT) * 10
				- skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10 > 1)
				&& (skeleton.get3DJointX(Skeleton.SHOULDER_RIGHT) * 10
						- skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10 < 3)) {
			return true;
		}
		return false;
	}

	public boolean closeLeft() {
		if ((skeleton.get3DJointX(Skeleton.HAND_LEFT) * 10
				- skeleton.get3DJointX(Skeleton.SHOULDER_LEFT) * 10 > 0)
				&& (skeleton.get3DJointX(Skeleton.HAND_LEFT) * 10
						- skeleton.get3DJointX(Skeleton.SHOULDER_LEFT) * 10 < 3)) {
			return true;
		}
		return false;
	}
}
