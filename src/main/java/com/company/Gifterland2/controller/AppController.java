package com.company.Gifterland2.controller;

import com.company.Gifterland2.model.Gastronomy;
import com.company.Gifterland2.service.GastronomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/")
public class AppController {

	@Autowired
    GastronomyService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing gastronomies.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listGastronomies(ModelMap model) {

		List<Gastronomy> gastronomies = service.findAllGastronomies();
		model.addAttribute("gastronomies", gastronomies);
		return "allgastronomies";
	}

	/*
	 * This method will provide the medium to add a new gastronomy.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newGastronomy(ModelMap model) {
		Gastronomy gastronomy = new Gastronomy();
		model.addAttribute("gastronomy", gastronomy);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving gastronomy in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveGastronomy(@Valid Gastronomy gastronomy, BindingResult result,
                               ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Gastronomy].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isGastronomySsnUnique(gastronomy.getId(), gastronomy.getSsn())){
			FieldError ssnError =new FieldError("gastronomy","ssn",messageSource.getMessage("non.unique.ssn", new String[]{gastronomy.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}
		
		service.saveGastronomy(gastronomy);

		model.addAttribute("success", "Gastronomy " + gastronomy.getName() + " registered successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.GET)
	public String editGastronomy(@PathVariable String ssn, ModelMap model) {
		Gastronomy gastronomy = service.findGastronomyBySsn(ssn);
		model.addAttribute("gastronomy", gastronomy);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating gastronomy in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{ssn}-gastronomy" }, method = RequestMethod.POST)
	public String updateGastronomy(@Valid Gastronomy gastronomy, BindingResult result,
                                 ModelMap model, @PathVariable String ssn) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isGastronomySsnUnique(gastronomy.getId(), gastronomy.getSsn())){
			FieldError ssnError =new FieldError("gastronomy","ssn",messageSource.getMessage("non.unique.ssn", new String[]{gastronomy.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}

		service.updateGastronomy(gastronomy);

		model.addAttribute("success", "Gastronomy " + gastronomy.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn) {
		service.deleteGastronomyBySsn(ssn);
		return "redirect:/list";
	}

}
