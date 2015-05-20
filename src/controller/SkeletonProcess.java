package controller;

import java.util.Vector;

public class SkeletonProcess {

	private float right_handX = 0;
	private float right_handY = 0;
	private float left_handX = 0;
	private float left_handY = 0;
	private float right_elbowX = 0;
	private float right_elbowY = 0;
	private float left_elbowX = 0;
	private float left_elbowY = 0;
	private float right_shoulderX = 0;
	private float right_shoulderY = 0;
	private float left_shoulderX = 0;
	private float left_shoulderY = 0;
	private float neckX = 0;
	private float neckY = 0;
	Vector [] waveResult = new Vector[4];

	public SkeletonProcess() {
	}

	public float getRight_handX() {
		return right_handX;
	}

	public void setRight_handX(float right_handX) {
		this.right_handX = right_handX * 10;
	}

	public float getRight_handY() {
		return right_handY;
	}

	public void setRight_handY(float right_handY) {
		this.right_handY = right_handY * 10;
	}

	public float getLeft_handX() {
		return left_handX;
	}

	public void setLeft_handX(float left_handX) {
		this.left_handX = left_handX * 10;
	}

	public float getLeft_handY() {
		return left_handY;
	}

	public void setLeft_handY(float left_handY) {
		this.left_handY = left_handY * 10;
	}

	public float getRight_elbowX() {
		return right_elbowX;
	}

	public void setRight_elbowX(float right_elbowX) {
		this.right_elbowX = right_elbowX * 10;
	}

	public float getRight_elbowY() {
		return right_elbowY;
	}

	public void setRight_elbowY(float right_elbowY) {
		this.right_elbowY = right_elbowY * 10;
	}

	public float getLeft_elbowX() {
		return left_elbowX;
	}

	public void setLeft_elbowX(float left_elbowX) {
		this.left_elbowX = left_elbowX * 10;
	}

	public float getLeft_elbowY() {
		return left_elbowY;
	}

	public void setLeft_elbowY(float left_elbowY) {
		this.left_elbowY = left_elbowY * 10;
	}

	public float getRight_shoulderX() {
		return right_shoulderX;
	}

	public void setRight_shoulderX(float right_shoulderX) {
		this.right_shoulderX = right_shoulderX * 10;
	}

	public float getRight_shoulderY() {
		return right_shoulderY;
	}

	public void setRight_shoulderY(float right_shoulderY) {
		this.right_shoulderY = right_shoulderY * 10;
	}

	public float getLeft_shoulderX() {
		return left_shoulderX;
	}

	public void setLeft_shoulderX(float left_shoulderX) {
		this.left_shoulderX = left_shoulderX * 10;
	}

	public float getLeft_shoulderY() {
		return left_shoulderY;
	}

	public void setLeft_shoulderY(float left_shoulderY) {
		this.left_shoulderY = left_shoulderY * 10;
	}

	public float getNeckX() {
		return neckX;
	}

	public void setNeckX(float neckX) {
		this.neckX = neckX * 10;
	}

	public float getNeckY() {
		return neckY;
	}

	public void setNeckY(float neckY) {
		this.neckY = neckY * 10;
	}

	// idea : using percentage so different size people can cope with same
	// constant value
	public int getDistanceFromRightOfNeck() {
		return Math.round(right_handX) - Math.round(neckX);
	}

	public int getDistanceFromLeftOfNect() {
		return Math.round(left_handX) - Math.round(neckX);
	}

	public int getDistanceFromRightElbowY() {
		return Math.round(right_handY) - Math.round(right_elbowY);
	}
	
	public boolean partWaveLeft(){
		if(right_handY >  right_elbowY){
			if(right_handX < right_elbowX){
				//wave hand to left
				return true;
			}
		}
		return false;
	}
	
	public boolean partWaveRight(){
		if(right_handY >  right_elbowY){
			if(right_handX > right_elbowX){
				//wave hand to left
				return true;
			}
		}
		return false;
	}
	
	public boolean detectHandWave(){
		
		long start = System.currentTimeMillis();
		
		long end = System.currentTimeMillis();
		
		
		return false;
	}

	public boolean moveForward() {
		if (right_handX > right_shoulderX) {
			if(right_handY > right_shoulderY){
				return true;
			}
		}
		return false;
	}

	public boolean moveBackward() {
		if ((right_handY < right_elbowY) && (right_handX > right_shoulderX)) {
			return true;
		}
		return false;
	}

	public boolean moveRight() {
		if ((left_handY > left_shoulderY) && (left_handX < left_shoulderX)) {
			return true;
		}
		return false;
	}

	public boolean moveLeft() {
		if ((left_handY < left_elbowY) && (left_handX < left_shoulderX)) {
			return true;
		}
		return false;
	}

	public boolean armUp() {
		if (((right_handX < right_shoulderX) && (left_handX > left_shoulderX))
				&& ((right_handY > right_shoulderY) && (left_handY > left_shoulderY))) {
			return true;
		}
		return false;
	}

	public boolean armDown() {
		if ((right_handX < right_shoulderX) && (left_handX > left_shoulderX)
				&& ((right_handY < right_elbowY) && (left_handY < left_elbowY))) {
			return true;
		}
		return false;
	}

	public int translateToMovement() {
		int movement = 0;

		if (armUp()) {
			movement = 5;
		} else if (armDown()) {
			movement = 6;
		} else if (moveForward()) {
			movement = 1;
		} else if (moveBackward()) {
			movement = 2;
		} else if (moveRight()) {
			movement = 3;
		} else if (moveLeft()) {
			movement = 4;
		} else {
			movement = 0;
		}
		return movement;
	}

}
