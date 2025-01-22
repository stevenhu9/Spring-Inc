package com.spring_inc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pilot")
public class Pilot {
	
	// Table columns
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pilot_id")
	private int pilotId;
	
	@Column(name = "pilot_rank")
	private String pilotRank;
	
	@Column(name = "flight_hours_logged")
	private int flightHoursLogged;
	
	@Column(name = "license")
	private String license;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "aircraft")
	private String aircraft;
	
	@Column(name = "commanderId")
	private int commanderId;
	
	@Column(name = "squadronId")
	private int squadronId;
	
	// default constructor for Hibernate bean
	public Pilot() {}
	

	//constructor, getters, setters, and toString
	public Pilot(int pilotId, String pilotRank, int flightHoursLogged, String license, String fullName, String aircraft,
			int commanderId, int squadronId) {
		super();
		this.pilotId = pilotId;
		this.pilotRank = pilotRank;
		this.flightHoursLogged = flightHoursLogged;
		this.license = license;
		this.fullName = fullName;
		this.aircraft = aircraft;
		this.commanderId = commanderId;
		this.squadronId = squadronId;
	}

	public int getPilotId() {
		return pilotId;
	}

	public void setPilotId(int pilotId) {
		this.pilotId = pilotId;
	}

	public String getPilotRank() {
		return pilotRank;
	}

	public void setPilotRank(String pilotRank) {
		this.pilotRank = pilotRank;
	}

	public int getFlightHoursLogged() {
		return flightHoursLogged;
	}

	public void setFlightHoursLogged(int flightHoursLogged) {
		this.flightHoursLogged = flightHoursLogged;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public int getCommanderId() {
		return commanderId;
	}

	public void setCommanderId(int commanderId) {
		this.commanderId = commanderId;
	}

	public int getSquadronId() {
		return squadronId;
	}

	public void setSquadronId(int squadronId) {
		this.squadronId = squadronId;
	}

	@Override
	public String toString() {
		return "Pilot [pilotId=" + pilotId + ", pilotRank=" + pilotRank + ", flightHoursLogged=" + flightHoursLogged
				+ ", license=" + license + ", fullName=" + fullName + ", aircraft=" + aircraft + ", commanderId="
				+ commanderId + ", squadronId=" + squadronId + "]";
	};	
}
