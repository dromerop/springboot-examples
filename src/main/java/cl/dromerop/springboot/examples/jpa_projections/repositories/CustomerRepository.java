package cl.dromerop.springboot.examples.jpa_projections.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.dromerop.springboot.examples.jpa_projections.domain.Customer;
import cl.dromerop.springboot.examples.jpa_projections.projections.CustomerProjection;

public interface CustomerRepository extends JpaRepository<Customer, Long> {    
    	
	//Native JpaRepository method
    Customer findOne(Long id);
    
    //Native JpaRepository method
    List<Customer> findAll();
    
    //Returning projections requires the query to have aliases (id, firstName)
	@Query("SELECT c.id AS id, firstName AS firstName FROM Customer c WHERE c.id = ?1")
	CustomerProjection findOneProjected(Long id);
	
    //Returning projections requires the query to have aliases (id, firstName)
	@Query("SELECT c.id AS id, firstName AS firstName FROM Customer c")
	List<CustomerProjection> findAllProjected();

}
