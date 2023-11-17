package com.app;

import java.util.Date;

public class Movies {
	private int movieId;
	private String title;
	private Date releaseDate;
	
	public Movies() {
	
	}

	public Movies(int movieId, String title, Date releaseDate) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.releaseDate = releaseDate;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Movies [movieId=" + movieId + ", title=" + title + ", releaseDate=" + releaseDate + "]";
	}
	
	
}
