package com.example.Campus.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Track")
public class Track {
	
	@Id
	@Column(name="trackid")
	private int trackid;
	
	@Column(name="trackname")
	private String trackname;
	
	@Column(name="strength")
	private int strength;

	@OneToMany(cascade = CascadeType.ALL)
    private List<Mind> minds;
	
	
	public List<Mind> getMinds() {
		return minds;
	}

	public void setMinds(List<Mind> minds) {
		this.minds = minds;
	}

	public Track() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Track(int trackid, String trackname, int strength) {
		super();
		this.trackid = trackid;
		this.trackname = trackname;
		this.strength = strength;
	}

	public int getTrackid() {
		return trackid;
	}

	public void setTrackid(int trackid) {
		this.trackid = trackid;
	}

	public String getTrackname() {
		return trackname;
	}

	public void setTrackname(String trackname) {
		this.trackname = trackname;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}


	
	
	
	
	
	
}
