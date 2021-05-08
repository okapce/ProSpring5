package com.prospring.ch9.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


@Entity
@Table(name = "album")
public class Album implements Serializable {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
