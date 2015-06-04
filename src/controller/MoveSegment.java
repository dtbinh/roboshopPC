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
		}

		return id;
	}

	public boolean moveForward() {
		if (((skeleton.get3DJointY(Skeleton.HAND_RIGHT) - skeleton
				.get3DJointY(Skeleton.SHOULDER_RIGHT)) > 1)
				&& ((skeleton.get3DJointY(Skeleton.HAND_RIGHT) - skeleton
						.get3DJointY(Skeleton.SHOULDER_RIGHT)) < 3)) {
			return true;
		}
		return false;
	}

	public boolean moveBackward() {
		if (skeleton.get3DJointY(Skeleton.HAND_RIGHT) > skeleton
				.get3DJointY(Skeleton.SHOULDER_RIGHT)) {
			return true;
		}
		return false;
	}

}
