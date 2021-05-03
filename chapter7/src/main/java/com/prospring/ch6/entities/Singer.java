package com.prospring.ch6.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "singer")
public class Singer implements Serializable {
 private Long id;
 private String firstName;
 private String lastName;
 private Date birthDate;
 private int version;
 
 private Set<Instrument> instruments = new HashSet<>();
 @ManyToMany
 @JoinTable(name = "singer_instrument",
 joinColumns = @JoinColumn(name = "SINGER_ID"),
 inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
 public Set<Instrument> getInstruments() {
 return instruments;
 }
 
 public void setInstruments(Set<Instrument> instruments) {
	 this.instruments = instruments;
	 }
 
 public void setId(Long id) {
 this.id = id;
 }
 @Id
 @GeneratedValue(strategy = IDENTITY)
 @Column(name = "ID")
 public Long getId() {
 return this.id;
 }
 @Version
 @Column(name = "VERSION")
 public int getVersion() {
 return version;
 }
 @Column(name = "FIRST_NAME")
 public String getFirstName() {
 return this.firstName;
 }
 @Column(name = "LAST_NAME")
 public String getLastName() {
 return this.lastName;
 }
 @Temporal(TemporalType.DATE)
 @Column(name = "BIRTH_DATE")
 public Date getBirthDate() {
 return birthDate;
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
 public void setVersion(int version) {
 this.version = version;
 }
 public String toString() {
 return "Singer - Id: " + id + ", First name: " + firstName
 + ", Last name: " + lastName + ", Birthday: " + birthDate;
 }
}