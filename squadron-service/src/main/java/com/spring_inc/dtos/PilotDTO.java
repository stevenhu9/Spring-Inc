package com.spring_inc.dtos;

public class PilotDTO {
	
	private int id;
	private String pilotRank;
	private int flightHoursLogged;
	private String license;
	private String fullName;
	private String aircraft;
	private int commanderId;
	private int squadronId;
	
	public PilotDTO(int id, String pilotRank, int flightHoursLogged, String license, String fullName, String aircraft,
			int commanderId, int squadronId) {
		super();
		this.setId(id);
		this.setPilotRank(pilotRank);
		this.setFlightHoursLogged(flightHoursLogged);
		this.setLicense(license);
		this.setFullName(fullName);
		this.setAircraft(aircraft);
		this.setCommanderId(commanderId);
		this.setSquadronId(squadronId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
