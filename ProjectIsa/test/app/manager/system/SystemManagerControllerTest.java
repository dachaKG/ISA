package app.manager.system;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemManagerControllerTest {

	@Autowired
	SystemManagerController SystemManagerController;
	
	@Autowired
	SystemManagerService SystemManagerService;
	
	@Autowired
	RestaurantService restaurantService;
	
	PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver();

	private MockMvc mvc;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(SystemManagerController).setCustomArgumentResolvers(pageResolver)
				.build();
	}
	
	@Test
	public void checkRights() throws Exception {
		mvc.perform(get("/systemManager/checkRights").sessionAttr("user",getManager(1l))).andExpect(status().isOk());
	}

	@Test
	public void findAllRestaurantManagers() throws Exception {
		MvcResult mock = mvc.perform(get("/systemManager/restaurantManager")).andExpect(status().isOk()).andReturn();
		String content = mock.getResponse().getContentAsString();
		assertNotNull(content);
	}
	
	@Test
	public void findSystemManager() throws Exception {
		MvcResult mock = mvc.perform(get("/systemManager").sessionAttr("user",getManager(1l))).andExpect(status().isOk()).andReturn();
		String content = mock.getResponse().getContentAsString();
		assertNotNull(content);
	}
	
	@Test
	public void findAllFreeRestaurantManagers() throws Exception {
		MvcResult mock = mvc.perform(get("/systemManager/freeRestaurantManager")).andExpect(status().isOk()).andReturn();
		String content = mock.getResponse().getContentAsString();
		assertNotNull(content);
	}
	
	@Test
	public void saveRestaurantManager() throws Exception {
		MvcResult mock = mvc.perform(post("/systemManager/restaurantManager").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getManager()))).andExpect(status().isCreated()).andReturn();;
		String content = mock.getResponse().getContentAsString();
		assertNotNull(content);
		
		
	}
	
	@Test
	public void saveRestaurant() throws Exception {
		MvcResult mock = mvc.perform(post("/systemManager/restaurant").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getRestaurant()))).andExpect(status().isCreated()).andReturn();
		String content = mock.getResponse().getContentAsString();
		assertNotNull(content);
		
		
	}
	

	private SystemManager getManager(Long id) {
		SystemManager systemManager = new SystemManager();
		systemManager.setId(id);
		systemManager.setFirstname("aaa");
		systemManager.setLastname("bbb");
		systemManager.setMail("2@2.com");
		systemManager.setPassword("2");
		systemManager.setRegistrated("1");
		return systemManager;
	}
	
	private SystemManager getManager() {
		SystemManager systemManager = new SystemManager();
		systemManager.setFirstname("aaa");
		systemManager.setLastname("bbb");
		systemManager.setMail("2asd@2.com");
		systemManager.setPassword("2");
		systemManager.setRegistrated("1");
		return systemManager;
	}
	
	private Restaurant getRestaurant() {
		Restaurant restaurant = new Restaurant();
		restaurant.setBartenders(new ArrayList<>());
		restaurant.setBidders(new ArrayList<>());
		restaurant.setChangedShiftsForBartenders(new ArrayList<>());
		restaurant.setChangedShiftsForCooks(new ArrayList<>());
		restaurant.setChangedShiftsForWaiters(new ArrayList<>());
		restaurant.setCity("aaa");
		restaurant.setCooks(new ArrayList<>());
		restaurant.setCountry("bb");
		restaurant.setDescription("sad");
		restaurant.setDrinks(new ArrayList<>());
		restaurant.setFood(new ArrayList<>());
		restaurant.setName("aasd");
		restaurant.setWaiters(new ArrayList<>());
		restaurant.setSummRate(0.0);
		restaurant.setStreet("asd");
		restaurant.setSegments(new ArrayList<>());
		restaurant.setRestaurantOrders(new ArrayList<>());
		restaurant.setRestaurantManagers(new ArrayList<>());
		restaurant.setNumber("asd");
		restaurant.setRateRestaurant(new ArrayList<>());
		restaurant.setOrder(new ArrayList<>());
		return restaurant;
	}

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
