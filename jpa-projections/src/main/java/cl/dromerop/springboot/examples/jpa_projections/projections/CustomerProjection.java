package cl.dromerop.springboot.examples.jpa_projections.projections;

/*
 * Getting just id and firstName
 */
public interface CustomerProjection {

	public Long getId();
	public String getFirstName();
	
}
