package cl.dromerop.springboot.examples.jpa_projections.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.dromerop.springboot.examples.jpa_projections.domain.Customer;
import cl.dromerop.springboot.examples.jpa_projections.projections.CustomerProjection;
import cl.dromerop.springboot.examples.jpa_projections.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRespository;
	
    public Customer findOne(Long id) {
    	return customerRespository.findOne(id);
    }
    
    public List<Customer> findAll() {
    	return customerRespository.findAll();
    }
    
    public CustomerProjection findOneProjected(Long id) {
    	return customerRespository.findOneProjected(id);
    }
	
    public List<CustomerProjection> findAllProjected() {
    	return customerRespository.findAllProjected();
    }
}
