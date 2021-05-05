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
}
