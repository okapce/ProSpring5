package com.prospring.ch8.entities;

	import java.io.Serializable;
	import javax.persistence.Entity;
	import javax.persistence.Table;
	import javax.persistence.Column;
	import javax.persistence.Id;
	import javax.persistence.ManyToMany;
	import javax.persistence.JoinTable;
	import javax.persistence.JoinColumn;
	import java.util.Set;
	import java.util.HashSet;
	
	@Entity
	@Table(name = "instrument")
	public class Instrument implements Serializable {
	 @Id
	 @Column(name = "INSTRUMENT_ID")
	 private String instrumentId;
	 @ManyToMany
	 @JoinTable(name = "singer_instrument",
	 joinColumns = @JoinColumn(name = "INSTRUMENT_ID"),
	 inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
	 private Set<Singer> singers = new HashSet<>();
	 //setters and getters
	public String getInstrumentId() {
		return instrumentId;
	}
	public Set<Singer> getSingers() {
		return singers;
	}
	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}
	public void setSingers(Set<Singer> singers) {
		this.singers = singers;
	}
	 
}
