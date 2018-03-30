package cl.dromerop.springboot.examples.jpa_projections.services;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cl.dromerop.springboot.examples.jpa_projections.domain.Customer;
import cl.dromerop.springboot.examples.jpa_projections.projections.CustomerProjection;
import cl.dromerop.springboot.examples.jpa_projections.repositories.CustomerRepository;
import cl.dromerop.springboot.examples.jpa_projections.specifications.CustomerSpecification;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
    public Customer findOne(Long id) {
    	return customerRepository.findOne(id);
    }
    
    public List<Customer> findAll() {
    	return customerRepository.findAll();
    }
    
    public CustomerProjection findOneProjected(Long id) {
    	return customerRepository.findOneProjected(id);
    }
	
    public List<CustomerProjection> findAllProjected() {
    	return customerRepository.findAllProjected();
    }
    
    public Customer findOneWithSpec(Long id) {
    	
		return customerRepository.findOne(where(CustomerSpecification.hasId(id)));
		
    }
    
    public List<Customer> findAllGreaterThanWithSpec(Long id) {
    	
    	Specification<Customer> where = where(CustomerSpecification.idGreaterThan(id));
    	
		return customerRepository.findAll(where);
		
    }
    
    public CustomerProjection findOneProjectedWithSpec(Long id) {

    	Specification<Customer> where = where(CustomerSpecification.hasId(id));

    	return customerRepository.findOneProjected(where);

    }
    
    public List<CustomerProjection> findAllProjectedGreaterThanWithSpec(Long id) {

    	Specification<Customer> where = where(CustomerSpecification.idGreaterThan(id));

    	return customerRepository.findAllProjected(where);

    }
    
}
