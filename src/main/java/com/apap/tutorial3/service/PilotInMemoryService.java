package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.PilotModel;

@Service
public class PilotInMemoryService implements PilotService{
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}
	
	@Override
	public List<PilotModel> getPilotList() {
		return archivePilot;
	}
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		for(int i=0; i<this.getPilotList().size() ; i++) {
			PilotModel pilot = this.getPilotList().get(i);
			if(pilot.getLicenseNumber().equals(licenseNumber)) {
				return pilot;
			}
		}return null;
	}
	
	public void deletePilot(PilotModel pilot) {
		archivePilot.remove(pilot);
	}
}
