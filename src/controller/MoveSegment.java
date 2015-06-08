package controller;

import edu.ufl.digitalworlds.j4k.Skeleton;

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
				.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) > 1
				&& (skeleton.get3DJointY(Skeleton.HAND_RIGHT) * 10 - skeleton
						.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) < 3) {
			return true;
		}
		return false;
	}

	public boolean moveBackward() {
		if ((skeleton.get3DJointY(Skeleton.HAND_RIGHT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) < -1
				&& (skeleton.get3DJointY(Skeleton.HAND_RIGHT) * 10 - skeleton
						.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) > -3) {
			return true;
		}
		return false;
	}

	public boolean rotateRight() {
		if ((skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) > 2
				&& (skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10 - skeleton
						.get3DJointX(Skeleton.SHOULDER_RIGHT) * 10) < 4) {
			return true;
		}
		return false;
	}

	public boolean rotateLeft() {
		if ((skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10 - skeleton
				.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10) < 1
				&& (skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10 - skeleton
						.get3DJointX(Skeleton.SHOULDER_RIGHT) * 10) > -4) {
			return true;
		}
		return false;
	}
}
