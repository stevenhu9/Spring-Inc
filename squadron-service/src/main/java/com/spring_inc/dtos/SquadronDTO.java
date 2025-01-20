package com.Spring_inc.dtos;

import java.sql.Timestamp;

public class SquadronDTO {
	
	private String squadronName;
	private String base;
	private java.sql.Timestamp dateFormed;
	private String mission;
	private int capacity;
	private String status;
	private int commanderId;
	
	
	public SquadronDTO(String squadronName, String base, Timestamp dateFormed, String mission, int capacity,
			String status, int commanderId) {
		super();
		this.squadronName = squadronName;
		this.base = base;
		this.dateFormed = dateFormed;
		this.mission = mission;
		this.capacity = capacity;
		this.status = status;
		this.commanderId = commanderId;
	}
	
	public String getSquadronName() {
		return squadronName;
	}
	public void setSquadronName(String squadronName) {
		this.squadronName = squadronName;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public java.sql.Timestamp getDateFormed() {
		return dateFormed;
	}
	public void setDateFormed(java.sql.Timestamp dateFormed) {
		this.dateFormed = dateFormed;
	}
	public String getMission() {
		return mission;
	}
	public void setMission(String mission) {
		this.mission = mission;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SquadronDTO [squadronName=" + squadronName + ", base=" + base + ", dateFormed=" + dateFormed
				+ ", mission=" + mission + ", capacity=" + capacity + ", status=" + status + "]";
	}

	public int getCommanderId() {
		return commanderId;
	}

	public void setCommanderId(int commanderId) {
		this.commanderId = commanderId;
	}

	
	
	
	
}
