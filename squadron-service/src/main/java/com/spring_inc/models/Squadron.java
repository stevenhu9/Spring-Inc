package com.spring_inc.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "squadron")
public class Squadron {

	// Table columns
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "squadron_id")
	private int squadronId;
	
	@Column(name = "squadron_name")
	private String squadronName;
	
	@Column(name = "base")
	private String base;
	
	@Column(name = "date_formed")
	private java.sql.Timestamp dateFormed;
	
	@Column(name = "mission")
	private String mission;
	
	@Column(name = "capacity")
	private int capacity;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "commanderId")
	private int commanderId;
	
	// default constructor for Hibernate bean
	public Squadron() {};
	
	//constructor, getters, setters, and toString
	public Squadron(int squadronId, String squadronName, String base, Timestamp dateFormed, String mission,
			int capacity, String status, int commanderId) {
		super();
		this.squadronId = squadronId;
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
	
}
