package app.employed.waiter;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.employed.ClothesSize;
import app.employed.DefaultShift;
import app.employed.ShoesSize;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WaiterControllerTest {
	
	@Autowired
	WaiterController waiterController;
	
	private MockMvc mockMvc;  
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	protected MockHttpSession session;
	
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		Waiter w = getWaiter();
		session = new MockHttpSession();
		session.setAttribute("user", w);
		
	}
	@Ignore
	@Test
	public void testWaiterController() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckRights() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/waiter/checkRights").session(session)).andDo(print()).andExpect(status().isOk());
	}

	
	@Test
	public void testFindWaiter() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/waiter").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.mail").value("9@9"));

	}

	@Ignore
	@Test
	public void testOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadyOrder() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/waiter/readyOrders").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(2)));
	}

	@Test
	public void employedWaiters() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/waiter/employedWaiters").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(4)));
	}
	@Ignore
	@Test
	public void testSendToEmployed() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() throws Exception {
		
		
	}
	@Ignore
	@Test
	public void testFindOne() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testUpdate() throws Exception {
		Waiter waiter = getWaiter();
		//String waiterPut = asJsonString(waiter);
		 mockMvc.perform(put("/waiter/profile/{id}", waiter.getId())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(waiter)))
	                .andExpect(status().isOk());
	}
	@Ignore
	@Test
	public void testChangePassword() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testMakeBill() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testBillForWaiter() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testSecondWaiter() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testChangeOrder() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetRestaurant() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGuestAddDrink() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGuestAddDish() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testNewOrder() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testNewOrderDish() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testNewOrderDrink() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testRemoveNewDish() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testRemoveNewDrink() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testMakeNewOrder() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testRemoveDish() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testRemoveDrink() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetReservations() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testWaiterTables() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetTables() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testChangeOrdersList() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetActiveReservations() {
		fail("Not yet implemented");
	}
	
	private Waiter getWaiter(){
		Waiter waiter = new Waiter();
		waiter.setId(1L);
		waiter.setFirstname("Pera");
		waiter.setLastname("Peric");
		waiter.setMail("9@9");
		waiter.setPassword("9");
		waiter.setRegistrated("0");
		waiter.setClothesSize(ClothesSize.S);
		waiter.setShoesSize(ShoesSize.no40);
		waiter.setDefaultShift(DefaultShift.First);
		waiter.setRate(2);
		return waiter;
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
