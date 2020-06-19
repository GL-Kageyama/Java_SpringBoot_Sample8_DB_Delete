package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller class for screen display and DB integration
 */
@Controller
public class RequestProc8 {

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Function for initial screen display
	 * @param model Model for storing customer information
	 * @return "index8" Return index8.html
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		// Obtain all customer information registered in the DB
		List<Customer> userlist = customerRepository.findAll();
		model.addAttribute("customerlist", userlist);

		return "/index8";
	}

	/**
	 * Function to display the customer information to be deleted
	 * @param num ID to be deleted
	 * @param model Model for storing customer information to be deleted
	 * @return "delete" Return delete.html
	 */
	@RequestMapping(value = "/{num}/delete", method = RequestMethod.GET)
	public String deleteValue(@PathVariable Long num, Model model) {

		// Get an ID
		Customer customer = customerRepository.findById(num).get();
		model.addAttribute("customerDelete", customer);

		return "delete";
	}

	/**
	 * Delete target customer information from the DB
	 * @param customerUpdate Customer information to be deleted
	 * @return "redirect:/" Return to root list.html
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute Customer customerDelete) {

		// Delete customer information from the DB
		customerRepository.delete(customerDelete);

		return "redirect:/";
	}
}

