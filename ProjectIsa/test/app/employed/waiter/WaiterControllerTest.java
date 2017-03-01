package app.employed.waiter;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

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

	
	@Test
	public void testOrders() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/waiter/orders").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(2)));
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
	
	@Test
	public void testSendToEmployed() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/waiter/sendToEmployed/{odrerId}", 12L)).andDo(print())
		.andExpect(status().isOk());/*.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.drinkStatus").value("inPrepared"));*/
	}


	@Test
	public void testUpdate() throws Exception {
		Waiter waiter = getWaiter();
		mockMvc.perform(put("/waiter/profile/{id}", waiter.getId())
	                       .contentType(MediaType.APPLICATION_JSON)
	                       .content(asJsonString(waiter)))
	               .andExpect(status().isOk());
	}
	
	@Test
	public void testWaiterTables() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/waiter/waiterTables").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(4)));
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
		waiter.setRate(2.2);
		waiter.setNumRate(new ArrayList<Integer>());
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
