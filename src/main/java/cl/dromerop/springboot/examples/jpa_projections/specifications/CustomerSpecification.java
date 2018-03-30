package cl.dromerop.springboot.examples.jpa_projections.specifications;

import org.springframework.data.jpa.domain.Specification;

import cl.dromerop.springboot.examples.jpa_projections.domain.Customer;

public class CustomerSpecification {

	public static Specification<Customer> hasId(Long id) {

		if (id == null)
			return null;

		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
				root.get("id"), id);
	}
	
	public static Specification<Customer> idGreaterThan(Long id) {

		if (id == null)
			return null;

		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(
				root.get("id"), id);
	}

}
