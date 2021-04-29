package com.prospring.ch6.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Singer implements Serializable {
	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private List<Album> albums;
	
	
	public boolean addAlbum(Album album) {
		if (albums == null) {
			albums = new ArrayList<>();
			albums.add(album);
			return true;
		} else {
			if (albums.contains(album)) {
				return false;
			}
		}
			 albums.add(album);
			 return true;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public List<Album> getAlbums() {
		return albums;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "Singer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", Birthday =" + birthDate
				+ "]";
	}
}
