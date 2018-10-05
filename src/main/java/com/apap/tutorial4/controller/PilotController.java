package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;

	@RequestMapping("/")
	private String home() {
		return "home";
	}

	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}

	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view")
	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if (archive == null) {
			return "view-error";
		}
		else {
			model.addAttribute("pilot", archive);
			return "view-pilot";
		}
	}
	
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String update(@PathVariable String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", archive);
		return "update-pilot";
	}

	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.POST)
	private String updateSubmit(@PathVariable String licenseNumber, @ModelAttribute PilotModel pilot, Model model) {
		pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber()).setName(pilot.getName());
		pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber()).setFlyHour(pilot.getFlyHour());
		System.out.println(pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber()).getFlyHour());
		return "updated";
	}
	
	@RequestMapping(value= "/pilot/delete/{id}")
	private String delete(@PathVariable Long id) {
		pilotService.removePilot(id);
		return "deleted";
	}
}
