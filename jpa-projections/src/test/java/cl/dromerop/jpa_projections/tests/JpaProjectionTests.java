package cl.dromerop.jpa_projections.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = cl.dromerop.springboot.examples.jpa_projections.app.Application.class)
@TestPropertySource(properties = { "spring.jmx.enabled:true",
		"spring.datasource.jmx-enabled:true" })
@ActiveProfiles("scratch")
public class JpaProjectionTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testAll() throws Exception {

		this.mvc.perform(get("/customer/find/all"))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string("[{\"id\":1,\"firstName\":\"Jack\",\"lastName\":\"Bauer\"},{\"id\":2,\"firstName\":\"Chloe\",\"lastName\":\"O'Brian\"},{\"id\":3,\"firstName\":\"Kim\",\"lastName\":\"Bauer\"},{\"id\":4,\"firstName\":\"David\",\"lastName\":\"Palmer\"},{\"id\":5,\"firstName\":\"Michelle\",\"lastName\":\"Dessler\"}]"));

	}

	// Projection only have id and first name
	@Test
	public void testAllProjected() throws Exception {

		this.mvc.perform(get("/customer/find/allprojected"))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string("[{\"id\":1,\"firstName\":\"Jack\"},{\"id\":2,\"firstName\":\"Chloe\"},{\"id\":3,\"firstName\":\"Kim\"},{\"id\":4,\"firstName\":\"David\"},{\"id\":5,\"firstName\":\"Michelle\"}]"));

	}

	@Test
	public void testAllWithSpecIdGreaterThan() throws Exception {

		this.mvc.perform(get("/customer/find/allwithspec/gt/4"))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string("[{\"id\":5,\"firstName\":\"Michelle\",\"lastName\":\"Dessler\"}]"));

	}
	
	// Projection only have id and first name
	@Test
	public void testAllProjectedWithSpecIdGreaterThan() throws Exception {

		this.mvc.perform(get("/customer/find/allprojectedwithspec/gt/4"))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string("[{\"id\":5,\"firstName\":\"Michelle\"}]"));

	}

	@Test
	public void testSingle() throws Exception {

		this.mvc.perform(get("/customer/find/single/1"))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string("{\"id\":1,\"firstName\":\"Jack\",\"lastName\":\"Bauer\"}"));

	}

	// Projection only have id and first name
	@Test
	public void testSingleProjected() throws Exception {

		this.mvc.perform(get("/customer/find/singleprojected/1"))
				.andExpect(status().isOk())
				.andExpect(
						content().string("{\"id\":1,\"firstName\":\"Jack\"}"));

	}

	@Test
	public void testSingleWithSpec() throws Exception {

		this.mvc.perform(get("/customer/find/singlewithspec/1"))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string("{\"id\":1,\"firstName\":\"Jack\",\"lastName\":\"Bauer\"}"));

	}

	// Projection only have id and first name
	@Test
	public void testSingleProjectedWithSpec() throws Exception {

		this.mvc.perform(get("/customer/find/singleprojectedwithspec/1"))
				.andExpect(status().isOk())
				.andExpect(
						content().string("{\"id\":1,\"firstName\":\"Jack\"}"));

	}

}
