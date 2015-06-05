package controller;

import java.util.Vector;

import edu.ufl.digitalworlds.j4k.Skeleton;

public class SkeletonProcess {
	private Skeleton skeleton;
	private float right_handX = 0;
	private float right_handY = 0;
	private float right_handZ = 0;
	private float left_handX = 0;
	private float left_handY = 0;
	private float left_handZ = 0;

	private float right_elbowX = 0;
	private float right_elbowY = 0;
	private float left_elbowX = 0;
	private float left_elbowY = 0;
	private float right_shoulderX = 0;
	private float right_shoulderY = 0;
	private float right_shoulderZ = 0;
	private float left_shoulderX = 0;
	private float left_shoulderY = 0;
	private float left_shoulderZ = 0;

	private float neckX = 0;
	private float neckY = 0;

	int waveCurSegment = 0;
	boolean[] waveResult = { false, false, false, false };
	boolean rightHandUp = false;
	boolean[] moveResult = { false, false, false, false };
	WaveSegment ws;
	MoveSegment ms;
	ArmSegment as;
	GripSegment gs;

	public SkeletonProcess() {
		ws = new WaveSegment();
	}

	public void setSkeleton(Skeleton skeleton) {
		this.skeleton = skeleton;
		ms = new MoveSegment(skeleton);
		as = new ArmSegment(skeleton);
		gs = new GripSegment(skeleton);
		setHandyName();
	}

	public Skeleton getSkeleton() {
		return skeleton;
	}

	public void setHandyName() {
		setLeft_handX();
		setLeft_handY();
		setLeft_handZ();
		setLeft_elbowX();
		setLeft_elbowY();
		setLeft_shoulderX();
		setLeft_shoulderY();
		setLeft_shoulderZ();
		setRight_handX();
		setRight_handY();
		setRight_handZ();
		setRight_elbowX();
		setRight_elbowY();
		setRight_shoulderX();
		setRight_shoulderY();
		setRight_shoulderZ();
		setNeckX();
		setNeckY();
	}

	public float getRight_handX() {
		return right_handX;
	}

	public void setRight_handX() {
		this.right_handX = skeleton.get3DJointX(Skeleton.HAND_RIGHT) * 10;
	}

	public float getRight_handY() {
		return right_handY;
	}

	public void setRight_handY() {
		this.right_handY = skeleton.get3DJointY(Skeleton.HAND_RIGHT) * 10;
	}

	public float getRight_handZ() {
		return right_handZ;
	}

	public void setRight_handZ() {
		this.right_handZ = skeleton.get3DJointZ(Skeleton.HAND_RIGHT) * 10;
	}

	public float getLeft_handX() {
		return left_handX;
	}

	public void setLeft_handX() {
		this.left_handX = skeleton.get3DJointX(Skeleton.HAND_LEFT) * 10;
	}

	public float getLeft_handY() {
		return left_handY;
	}

	public void setLeft_handY() {
		this.left_handY = skeleton.get3DJointY(Skeleton.HAND_LEFT) * 10;
	}

	public float getLeft_handZ() {
		return left_handZ;
	}

	public void setLeft_handZ() {
		this.left_handZ = skeleton.get3DJointZ(Skeleton.HAND_LEFT) * 10;
	}

	public float getRight_elbowX() {
		return right_elbowX;
	}

	public void setRight_elbowX() {
		this.right_elbowX = skeleton.get3DJointX(Skeleton.ELBOW_RIGHT) * 10;
	}

	public float getRight_elbowY() {
		return right_elbowY;
	}

	public void setRight_elbowY() {
		this.right_elbowY = skeleton.get3DJointY(Skeleton.ELBOW_RIGHT) * 10;
	}

	public float getLeft_elbowX() {
		return left_elbowX;
	}

	public void setLeft_elbowX() {
		this.left_elbowX = skeleton.get3DJointX(Skeleton.ELBOW_LEFT) * 10;
	}

	public float getLeft_elbowY() {
		return left_elbowY;
	}

	public void setLeft_elbowY() {
		this.left_elbowY = skeleton.get3DJointY(Skeleton.ELBOW_LEFT) * 10;
	}

	public float getRight_shoulderX() {
		return right_shoulderX;
	}

	public void setRight_shoulderX() {
		this.right_shoulderX = skeleton.get3DJointX(Skeleton.SHOULDER_RIGHT) * 10;
	}

	public float getRight_shoulderY() {
		return right_shoulderY;
	}

	public void setRight_shoulderY() {
		this.right_shoulderY = skeleton.get3DJointY(Skeleton.SHOULDER_RIGHT) * 10;
	}

	public float getRight_shoulderZ() {
		return right_shoulderZ;
	}

	public void setRight_shoulderZ() {
		this.right_shoulderZ = skeleton.get3DJointZ(Skeleton.SHOULDER_RIGHT) * 10;
	}

	public float getLeft_shoulderX() {
		return left_shoulderX;
	}

	public void setLeft_shoulderX() {
		this.left_shoulderX = skeleton.get3DJointX(Skeleton.SHOULDER_LEFT) * 10;
	}

	public float getLeft_shoulderY() {
		return left_shoulderY;
	}

	public void setLeft_shoulderY() {
		this.left_shoulderY = skeleton.get3DJointY(Skeleton.SHOULDER_LEFT) * 10;
	}

	public float getLeft_shoulderZ() {
		return left_shoulderZ;
	}

	public void setLeft_shoulderZ() {
		this.left_shoulderZ = skeleton.get3DJointZ(Skeleton.SHOULDER_LEFT) * 10;
	}

	public float getNeckX() {
		return neckX;
	}

	public void setNeckX() {
		this.neckX = skeleton.get3DJointX(Skeleton.NECK) * 10;
	}

	public float getNeckY() {
		return neckY;
	}

	public void setNeckY() {
		this.neckY = skeleton.get3DJointX(Skeleton.NECK) * 10;
	}

	public boolean getWaveCompleted() {
		// check all the wave part is completed, return false if one of it is
		// not complete
		boolean partWaveResult = false;

		if (waveCurSegment % 2 > 0) {
			partWaveResult = ws.wavePartRight(skeleton);
			System.out.println("Wave Right : " + partWaveResult);
		} else {
			partWaveResult = ws.wavePartLeft(skeleton);
			System.out.println("Wave Left : " + partWaveResult);
		}

		if (partWaveResult) {
			waveResult[waveCurSegment] = true;
			waveCurSegment++;
		}

		for (int i = 0; i < waveResult.length; i++) {
			if (!waveResult[i]) {
				return false;
			}
		}
		return true;
	}

	public int getMoveCompleted() {
		return ms.getMoveId();
	}

	public int getArmCompleted() {
		return as.getMoveId();
	}

	public int getGripCompleted() {
		return gs.getMoveId();
	}

	public int getToMovement() {
		int movement = 0;

		if ((right_shoulderZ - right_handZ > 2) && (left_shoulderZ - left_handZ > 2)) {
			movement = getGripCompleted();
		} else if ((right_shoulderZ - right_handZ) > 4) {
			movement = getMoveCompleted();
		} else if ((left_shoulderZ - left_handZ) > 4) {
			movement = getArmCompleted();
		}

		return movement;
	}
}
