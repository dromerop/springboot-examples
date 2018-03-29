package cl.dromerop.springboot.examples.jpa_projections.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.dromerop.springboot.examples.jpa_projections.domain.Customer;
import cl.dromerop.springboot.examples.jpa_projections.projections.CustomerProjection;
import cl.dromerop.springboot.examples.jpa_projections.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

	private static Logger logger = LoggerFactory.getLogger(CustomerRestController.class);

	@Autowired
	CustomerService customerService;

	public CustomerRestController() {
	}

	//WORKS
	@RequestMapping(method = RequestMethod.GET, value = "/find/all")
	ResponseEntity<?> findAll(Model model) {

		return new ResponseEntity<List<Customer>>(customerService.findAll(), HttpStatus.OK);

	}
	
	//WORKS
	@RequestMapping(method = RequestMethod.GET, value = "/find/allprojected")
	ResponseEntity<?> findAllProjected(Model model) {

		return new ResponseEntity<List<CustomerProjection>>(customerService.findAllProjected(), HttpStatus.OK);

	}

	//WORKS
	@RequestMapping(method = RequestMethod.GET, value = "/find/single/{id}")
	ResponseEntity<?> findSingle(Model model, @PathVariable("id") Long id) {

		return new ResponseEntity<Customer>(customerService.findOne(id), HttpStatus.OK);

	}
	
	//WORKS
	@RequestMapping(method = RequestMethod.GET, value = "/find/singleprojected/{id}")
	ResponseEntity<?> findSingleProjected(Model model, @PathVariable("id") Long id) {

		return new ResponseEntity<CustomerProjection>(customerService.findOneProjected(id), HttpStatus.OK);

	}
	
}
