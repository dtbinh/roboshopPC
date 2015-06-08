package controller;

import edu.ufl.digitalworlds.j4k.Skeleton;

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
