package cl.dromerop.springboot.examples.jpa_projections.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cl.dromerop.springboot.examples.jpa_projections.domain.Customer;
import cl.dromerop.springboot.examples.jpa_projections.repositories.CustomerRepository;

@SpringBootApplication
@ComponentScan({
	"cl.dromerop.springboot.examples.jpa_projections.services", 
	"cl.dromerop.springboot.examples.jpa_projections.repositories", 
	"cl.dromerop.springboot.examples.jpa_projections.controllers"})
@EntityScan("cl.dromerop.springboot.examples.jpa_projections.domain")
@EnableJpaRepositories("cl.dromerop.springboot.examples.jpa_projections.repositories")
public class Application extends WebMvcConfigurerAdapter {

	private static final Logger log = LoggerFactory
			.getLogger(Application.class);

	@Autowired
	CustomerRepository repository;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(
				Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");
			
		};
	}
}