package com.spring_inc.dtos;

public class CommanderDTO {

	private int id;
	private String commanderName;
	private String commanderRank;
	private int yearsOfService;
	private String specialization;
	private byte activeDuty;
	
	
	public CommanderDTO(int id, String commanderName, String commanderRank, int yearsOfService,String specialization,
			byte activeDuty) {
		super();
		this.id = id;
		this.commanderName = commanderName;
		this.commanderRank = commanderRank;
		this.specialization = specialization;
		this.yearsOfService = yearsOfService;
		this.activeDuty = activeDuty;
	}
	
	public int getid() {
		return id;
	}
	
	public void setid(int id) {
		this.id = id;
	}
	
	public String getCommanderName() {
		return commanderName;
	}
	public void setCommanderName(String commanderName) {
		this.commanderName = commanderName;
	}
	public String getCommanderRank() {
		return commanderRank;
	}
	public void setcommanderRank(String commanderRank) {
		this.commanderRank = commanderRank;
	}
	
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public int getYearsOfService() {
		return yearsOfService;
	}
	public void setYearsOfService(int yearsOfService) {
		this.yearsOfService = yearsOfService;
	}
	public byte getActiveDuty() {
		return activeDuty;
	}
	public void setActiveduty(byte activeDuty) {
		this.activeDuty = activeDuty;
	}

	@Override
	public String toString() {
		return "CommanderDTO [CommanderName=" + commanderName + ", rank=" + commanderRank + ", "
				+ "specialization=" + specialization + ", yearsOfService=" + yearsOfService + ", activeDuty=" + activeDuty + "]";
	}
	
}
