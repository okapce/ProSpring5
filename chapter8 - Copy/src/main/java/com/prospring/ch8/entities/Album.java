package com.prospring.ch8.entities;
import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name = "album")
public class Album implements Serializable {
 @Id
 @GeneratedValue(strategy = IDENTITY)
 @Column(name = "ID")
 private Long id;
 @Version
 @Column(name = "VERSION")
 private int version;
 @Column
 private String title;
 @Temporal(TemporalType.DATE)
 @Column(name = "RELEASE_DATE")
 private Date releaseDate;
 @ManyToOne
 @JoinColumn(name = "SINGER_ID")
 private Singer singer;
 public Album() {
 //needed byJPA
 }
 public Album(String title, Date releaseDate) {
 this.title = title;
 this.releaseDate = releaseDate;
 }
@Override
public String toString() {
	return "Album [id=" + id + ", version=" + version + ", title=" + title + ", releaseDate=" + releaseDate
			+ ", singer=" + singer + "]";
}
public Long getId() {
	return id;
}
public int getVersion() {
	return version;
}
public String getTitle() {
	return title;
}
public Date getReleaseDate() {
	return releaseDate;
}
public Singer getSinger() {
	return singer;
}
public void setId(Long id) {
	this.id = id;
}
public void setVersion(int version) {
	this.version = version;
}
public void setTitle(String title) {
	this.title = title;
}
public void setReleaseDate(Date releaseDate) {
	this.releaseDate = releaseDate;
}
public void setSinger(Singer singer) {
	this.singer = singer;
}


}
