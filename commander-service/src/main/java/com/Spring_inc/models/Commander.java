package com.Spring_inc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "commander")
public class Commander {
	
	// Table columns
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commander_id")
	private int commanderId;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "commander_rank")
	private String commanderRank;
	
	@Column(name = "years_of_service")
	private int yearsOfService;
	
	@Column(name = "specialization")
	private String specialization;
	
	@Column(name = "active_duty")
	private String activeDuty;
	
	
	// default constructor for Hibernate bean
	public Commander () {};
	
	//constructor, getters, setters, and toString
	public Commander(int commanderId, String fullName, String commanderRank, int yearsOfService, String specialization,
			String activeDuty) {
		super();
		this.commanderId = commanderId;
		this.fullName = fullName;
		this.commanderRank = commanderRank;
		this.yearsOfService = yearsOfService;
		this.specialization = specialization;
		this.activeDuty = activeDuty;
	}

	public int getCommanderId() {
		return commanderId;
	}

	public void setCommanderId(int CommanderId) {
		this.commanderId = CommanderId;
	}

	public String getCommanderName() {
		return fullName;
	}

	public void setCommanderName(String CommanderName) {
		this.fullName = CommanderName;
	}

	public String getCommanderRank() {
		return commanderRank;
	}

	public void setCommanderRank(String commanderRank) {
		this.commanderRank = commanderRank;
	}

	public int getYearsOfService() {
		return yearsOfService;
	}

	public void setYearsOfService(int yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialziation(String specialization) {
		this.specialization = specialization;
	}

	public String getActiveDuty() {
		return activeDuty;
	}

	public void setActiveDuty(String activeDuty) {
		this.activeDuty= activeDuty;
	}

	@Override
	public String toString() {
		return "Commander [CommanderId=" + commanderId + ", CommanderName=" + fullName + ", Rank=" + commanderRank
				+ ", yearsOfService=" + yearsOfService + ", Specialization=" + specialization + ", ActiveDutyy=" + activeDuty + ","
						+ "]";
	}
	
	
}
