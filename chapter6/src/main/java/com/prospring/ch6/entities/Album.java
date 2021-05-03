package com.prospring.ch6.entities;

import java.io.Serializable;
import java.sql.Date;

public class Album implements Serializable{
	private Long id;
	private Long singerId;
	private String title;
	private Date releaseDate;
	
	public Long getId() {
		return id;
	}
	public Long getSingerId() {
		return singerId;
	}
	public String getTitle() {
		return title;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setSingerId(Long singerId) {
		this.singerId = singerId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	@Override
	public String toString() {
		return "Album [Album id=" + id + ", singer Id=" + singerId + ", title=" + title + ", Release Date=" + releaseDate + "]";
	}
}
