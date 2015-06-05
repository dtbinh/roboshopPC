package controller;

import edu.ufl.digitalworlds.j4k.Skeleton;

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
