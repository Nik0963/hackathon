package com.app;

import java.util.Date;

public class Reviews {
	private int reviewId;
	private int movieId;
	private String review;
	private int rating;
	private int userId;
	private Date modified;
	
	public Reviews() {
	
	}

	public Reviews(int reviewId, int movieId, String review, int rating, int userId, Date modified) {
		super();
		this.reviewId = reviewId;
		this.movieId = movieId;
		this.review = review;
		this.rating = rating;
		this.userId = userId;
		this.modified = modified;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "Reviews [reviewId=" + reviewId + ", movieId=" + movieId + ", review=" + review + ", rating=" + rating
				+ ", userId=" + userId + ", modified=" + modified + "]";
	}
	
	
	
}
