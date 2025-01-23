package com.Spring_inc.dtos;

public class CommanderDTO {

	private String commanderName;
	private String commanderRank;
	private int yearsOfService;
	private String specialization;
	private String activeDuty;
	
	
	public CommanderDTO(String commanderName, String commanderRank, String specialization, int yearsOfService,
			String activeDuty) {
		super();
		this.commanderName = commanderName;
		this.commanderRank = commanderRank;
		this.specialization = specialization;
		this.yearsOfService = yearsOfService;
		this.activeDuty = activeDuty;
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
	public String getActiveDuty() {
		return activeDuty;
	}
	public void setActiveduty(String activeDuty) {
		this.activeDuty = activeDuty;
	}

	@Override
	public String toString() {
		return "CommanderDTO [CommanderName=" + commanderName + ", rank=" + commanderRank + ", "
				+ "specialization=" + specialization + ", yearsOfService=" + yearsOfService + ", activeDuty=" + activeDuty + "]";
	}
	
}
