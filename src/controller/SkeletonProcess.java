package controller;

public class SkeletonProcess {
	
	private float right_hand;
	private float neck;
	
	public SkeletonProcess(){
	}

	public float getRight_hand() {
		return right_hand;
	}

	public void setRight_hand(float right_hand) {
		this.right_hand = right_hand*100;
	}

	public float getNeck() {
		return neck;
	}

	public void setNeck(float neck) {
		this.neck = neck*100;
	}
	
	public int getDistanceFromRightOfNeck(){
		return Math.round(right_hand)-Math.round(neck);
	}


}
