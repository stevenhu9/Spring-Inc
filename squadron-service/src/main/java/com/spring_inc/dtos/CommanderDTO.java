package com.spring_inc.dtos;

public class CommanderDTO {

	private int id;
	private String commanderName;
	private String commanderRank;
	private int yearsOfService;
	private String specialization;
	private byte activeDuty;
	
	
	public CommanderDTO(int id, String commanderName, String commanderRank, int yearsOfService, String specialization,
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
	
	public String getCommanderName() {
		return commanderName;
	}

	public String getCommanderRank() {
		return commanderRank;
	}
	
	public String getSpecialization() {
		return specialization;
	}

	public int getYearsOfService() {
		return yearsOfService;
	}

	public byte getActiveDuty() {
		return activeDuty;
	}
	
}
