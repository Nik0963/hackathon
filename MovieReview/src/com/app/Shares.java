package com.app;

public class Shares {
	private int reviewId;
	private int userId;
	
	public Shares() {
	
	}

	public Shares(int reviewId, int userId) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Shares [reviewId=" + reviewId + ", userId=" + userId + "]";
	}
	
	
}
