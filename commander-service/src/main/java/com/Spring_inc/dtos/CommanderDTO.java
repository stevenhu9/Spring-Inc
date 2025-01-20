package com.Spring_inc.dtos;

import java.sql.Timestamp;

public class CommanderDTO {

	private String CommanderName;
	private String rank;
	private int yos;
	private String special;
	private String activeduty;
	//private int commanderId;
	
	
	public CommanderDTO(String CommanderName, String rank, String special, int yos,
			String activeduty) {
		super();
		this.CommanderName = CommanderName;
		this.rank = rank;
		this.special = special;
		this.yos = yos;
		this.activeduty = activeduty;
		//this.commanderId = commanderId;
	}
	
	public String getCommanderName() {
		return CommanderName;
	}
	public void setCommanderName(String CommanderName) {
		this.CommanderName = CommanderName;
	}
	public String getrank() {
		return rank;
	}
	public void setrank(String rank) {
		this.rank = rank;
	}
	
	public String getspecial() {
		return special;
	}
	public void setspecial(String special) {
		this.special = special;
	}
	public int getyos() {
		return yos;
	}
	public void setyos(int yos) {
		this.yos = yos;
	}
	public String getactiveduty() {
		return activeduty;
	}
	public void setactiveduty(String activeduty) {
		this.activeduty = activeduty;
	}

	@Override
	public String toString() {
		return "CommanderDTO [CommanderName=" + CommanderName + ", rank=" + rank + ", "
				+ "special=" + special + ", yos=" + yos + ", activeduty=" + activeduty + "]";
	}

	//public int getCommanderId() {
	//	return commanderId;
	//}

	//public void setCommanderId(int commanderId) {
	//	this.commanderId = commanderId;
	//}
	
}
