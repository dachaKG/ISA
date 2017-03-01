package app.employed.cooker;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import app.employed.cook.Cook;
import app.employed.cook.TypeOfCooker;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CookControllerTest {

	private MockMvc mockMvc;  
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	protected MockHttpSession session;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		Cook c = getCook();
		session = new MockHttpSession();
		session.setAttribute("user", c);
	}


	@Test
	public void testCheckRights() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/cook/checkRights").session(session)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void testEmployedCooks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/cook/employedCooks").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(2)));
	}

	@Test
	public void testFindAllOrdrers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/cook/orders").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(1)));
	}

	@Test
	public void testFoodReceived() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/cook/foodReceived/{odrerId}", 12L).session(session)).andDo(print())
		.andExpect(status().isOk());/*.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.checkVersion").value(1));*/
	}

	@Test
	public void testChangedShiftDate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/cook/changedShiftDate/{cookId}",1L)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(1)));
	}

	private Cook getCook(){
		Cook cook = new Cook();
		cook.setId(1L);
		cook.setFirstname("mika");
		cook.setLastname("mikic");
		cook.setMail("ss@ss");
		cook.setPassword("sifta");
		cook.setRegistrated("0");
		cook.setClothesSize(ClothesSize.M);
		cook.setShoesSize(ShoesSize.no39);
		cook.setDefaultShift(DefaultShift.Second);
		cook.setTypeOfCooker(TypeOfCooker.baked);
		return cook;
		
	}

	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
