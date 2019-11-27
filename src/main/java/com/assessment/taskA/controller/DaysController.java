package com.assessment.taskA.controller;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assessment.taskA.forms.DisplayDaysForm;

@Controller
public class DaysController {
	
	@Value("${invalidDate.message}")
	private String message;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getDisplayDaysForm(Model model) {
		DisplayDaysForm displayDaysForm = new DisplayDaysForm();
		model.addAttribute("displayDaysForm", displayDaysForm);
		return "index";
	}
	
	@RequestMapping(value="/displayDays", method=RequestMethod.POST)
	public String displayDays(@ModelAttribute(name="displayDaysForm") DisplayDaysForm displayDaysForm, Model model) {
		LocalDate startDate = LocalDate.parse(displayDaysForm.getStartDate());
		LocalDate endDate = LocalDate.parse(displayDaysForm.getEndDate());
		
		System.out.println(startDate);
		System.out.println(endDate);
		model.addAttribute("displayDaysForm", displayDaysForm);
		
		if(startDate.isAfter(endDate)) {
			model.addAttribute("message", message);
			
		}else {
			Period diff = startDate.until(endDate);
			System.out.println(diff.getDays());
			model.addAttribute("count", diff.getDays());
		}
				
		return "index";
	}

}
