package com.apap.tutorial3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;

	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "licenseNumber", required = true) String licenseNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel (id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping("/pilot/view/license-number/{licenseNumber}")
	public String viewlicense(@PathVariable String licenseNumber, Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		for (int i=0; i<archive.size(); i++) {
			PilotModel pilot = archive.get(i);
			
			if(pilot.getLicenseNumber().equals(licenseNumber)) {
				model.addAttribute("pilot", pilot);
				return "view-pilot";
			}
		}return "error-viewlincense";
	}
	
	@RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{hour}") 
	public String update(@PathVariable String licenseNumber, @PathVariable Integer hour, Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		for (int i=0; i<archive.size(); i++) {
			PilotModel pilot = archive.get(i);
			
			if(pilot.getLicenseNumber().equals(licenseNumber)) {
				pilot.setFlyHour(hour);
				model.addAttribute("pilot", pilot);
				return "update";
			}
		}return "error-update";
	}
	
	@RequestMapping("/pilot/delete/id/{id}") 
	public String update(@PathVariable String id, Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		for (int i=0; i<archive.size(); i++) {
			PilotModel pilot = archive.get(i);
			
			if(pilot.getId().equals(id)) {
				pilotService.deletePilot(pilot);
				model.addAttribute("pilot", pilot);
				return "delete";
			}
		}return "error-delete";
	}
	
}

