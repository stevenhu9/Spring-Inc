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
	private String rank;
	
	@Column(name = "years_of_service")
	private int YOS;
	
	@Column(name = "specialization")
	private String specialization;
	
	@Column(name = "active_duty")
	private String active_duty;
	
	// commander and Commander have a one to one relationship
	//@OneToOne(mappedBy = "commander", cascade = CascadeType.ALL)
	//@JsonIgnoreProperties("commander")
	//private Squadron squadron;
	
	// default constructor for Hibernate bean
	public Commander () {};
	
	//constructor, getters, setters, and toString
	public Commander(int commanderId, String fullName, String rank, int years_of_service, String specialization,
			String active_duty) {
		super();
		this.commanderId = commanderId;
		this.fullName = fullName;
		this.rank = rank;
		this.YOS = years_of_service;
		this.specialization = specialization;
		this.active_duty = active_duty;
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

	public String getRank() {
		return rank;
	}

	public void setRank(String Rank) {
		this.rank = Rank;
	}

	public int getYOS() {
		return YOS;
	}

	public void setYOS(int yos) {
		this.YOS = yos;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialziation(String spec) {
		this.specialization = spec;
	}

	public String getActiveDuty() {
		return active_duty;
	}

	public void setActiveDuty(String ad) {
		this.active_duty = ad;
	}

	//public int getSquadronId() {
		//return squadronId;
	//}

	//public void setSquadronId(int SquadronId) {
		//this.squadronId = SquadronId;
	//}

	@Override
	public String toString() {
		return "Commander [CommanderId=" + commanderId + ", CommanderName=" + fullName + ", Rank=" + rank
				+ ", Years_Of_Service=" + YOS + ", Specialization=" + specialization + ", Active_duty=" + active_duty + ","
						+ "]";
	}
	
	
}
