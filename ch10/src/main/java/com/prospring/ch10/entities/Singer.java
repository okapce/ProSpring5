package com.prospring.ch10.entities;

import java.net.URL;
import java.text.SimpleDateFormat;
import org.joda.time.DateTime;

import com.prospring.ch10.obj.Genre;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Singer {
	@NotNull
	@Size(min=2, max=60)
	private String firstName;
	
	private String lastName;
	private DateTime birthDate;
	private URL personalSite;
	
	@NotNull
	private Genre genre;
	
	private String gender;
	
	
	public Genre getGenre() {
		return genre;
	}

	public String getGender() {
		return gender;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public DateTime getBirthDate() {
		return birthDate;
	}
	
	public URL getPersonalSite() {
		return personalSite;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}
	
	public void setPersonalSite(URL personalSite) {
		this.personalSite = personalSite;
	}

	 public boolean isCountrySinger() {
		 return genre == Genre.COUNTRY;
	 }
	 
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return String.format("{First name: %s, Last name: %s, Birthday: %s, Site: %s}",
				firstName, lastName, sdf.format(birthDate.toDate()), personalSite);
	}
	
	
}
