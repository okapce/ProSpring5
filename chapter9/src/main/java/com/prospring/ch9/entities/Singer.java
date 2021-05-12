package com.prospring.ch9.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "singer")
@NamedQueries({
	 @NamedQuery(name=Singer.FIND_ALL, query="select s from \"MUSICDB\".Singer s"),  //can't have namedQueries with postgres
	 @NamedQuery(name=Singer.COUNT_ALL, query="select count(s) from \"MUSICDB\".Singer s") //must change to query and attach to object
	})
	public class Singer implements Serializable {
		 public static final String FIND_ALL = "Singer.findAll";
		 public static final String COUNT_ALL = "Singer.countAll";
		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 @Column(name = "ID")
		 private Long id;
		 @Version
		 @Column(name = "VERSION")
		 private int version;
		 @Column(name = "FIRST_NAME")
		 private String firstName;
		 @Column(name = "LAST_NAME")
		 private String lastName;
		 @Temporal(TemporalType.DATE)
		 @Column(name = "BIRTH_DATE")
		 private Date birthDate;
		 @OneToMany(mappedBy = "singer", cascade=CascadeType.ALL, orphanRemoval=true)
		 private Set<Album> albums = new HashSet<>();
	 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 
	 
}
