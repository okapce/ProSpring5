package com.prospring.ch7.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "album")
public class Album implements Serializable{
	private Long id;
	private Long singerId;
	private String title;
	private Date releaseDate;
	
	private Singer singer;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "ID")
	public Long getId() {
		return id;
	}
	public Long getSingerId() {
		return singerId;
	}
	
	@Column
	public String getTitle() {
		return title;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "RELEASE_DATE")
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setSingerId(Long singerId) {
		this.singerId = singerId;
	}
	
	@ManyToOne
	@JoinColumn(name = "SINGER_ID")
	public Singer getSinger() {
		return this.singer;
	}
	
	public void setSinger(Singer singer) {
		this.singer = singer;
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
