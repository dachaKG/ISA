package app.employed.bartender;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
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
import app.order.Orderr;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BartenderControllerTest {

	
	private MockMvc mockMvc;  
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	protected MockHttpSession session;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		Bartender b = getBartender();
		session = new MockHttpSession();
		session.setAttribute("user", b);
	}
	

	@Test
	public void testFindBartender() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/bartender").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.mail").value("a@gmail.com"));
		
	}

	@Test
	public void testSave() throws Exception {
		
		Bartender bartender = getBartenderFindOne();
		mockMvc.perform(put("/bartender/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bartender)))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.mail").value("danilo@gmail.com"));
		
	}
	
	

	@Test
	public void testEmployedBartenders() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/bartender/employedBartenders").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(2)));
	}

	@Test
	public void testUpdate() throws Exception {
		Bartender bartender = getBartenderFindOne();
		 mockMvc.perform(put("/bartender/profile")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(bartender)))
	                .andExpect(status().isOk());
	}

	@Test
	public void testChangePassword() throws Exception {
		Bartender bartender = getBartenderFindOne();
		bartender.setPassword("prodjiibreeeee");
		
		 mockMvc.perform(put("/bartender/changePassword/{id}", bartender.getId())
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(asJsonString(bartender)))
         .andExpect(status().isOk());/*andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
 		.andExpect(jsonPath("$.password").value("prodjiibreeeee"));*/
	}

	
	@Test
	public void testFindAllOrdrers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/bartender/orders").session(session)).andDo(print())
		.andExpect(status().isOk());/*andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(1)));*/
	}
	
	
	@Test
	public void testDrinkReady() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/bartender/drinkReady/{odrerId}", 12L).session(session)).andDo(print())
		.andExpect(status().isOk());/*andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.drinkStatus").value("finished"));*/
	}
	
	
	///
	
	private Bartender getBartender(){
		Bartender bartender = new Bartender();
		bartender.setId(1L);
		bartender.setFirstname("Danilo");
		bartender.setLastname("Dimitrijevic");
		bartender.setMail("vlado94@gmail.com");
		bartender.setPassword("ssss");
		bartender.setRegistrated("1");
		bartender.setClothesSize(ClothesSize.XL);
		bartender.setShoesSize(ShoesSize.no46);
		bartender.setDefaultShift(DefaultShift.First);
		bartender.setOrders(new ArrayList<Orderr>());
		return bartender;
	}
	
	private Bartender getBartenderFindOne(){
		Bartender bartender = new Bartender();
		bartender.setId(2L);
		bartender.setFirstname("Vlado");
		bartender.setLastname("Stanojevic");
		bartender.setMail("danilo@gmail.com");
		bartender.setPassword("olja");
		bartender.setRegistrated("1");
		bartender.setClothesSize(ClothesSize.XL);
		bartender.setShoesSize(ShoesSize.no46);
		bartender.setDefaultShift(DefaultShift.First);
		
		return bartender;
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
}
