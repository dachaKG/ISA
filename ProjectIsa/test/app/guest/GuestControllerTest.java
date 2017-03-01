package app.guest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestControllerTest {
	
	
	@Autowired
	GuestController guestController;
	
	@Autowired
	GuestService guestService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mvc;
	
	protected MockHttpSession session;
	
	PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver();
	
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(guestController).setCustomArgumentResolvers(pageResolver)
				.build();
		Guest g = getGuest(5l);
		session = new MockHttpSession();
		session.setAttribute("user", g);
		
	}

	@Test
	public void checkRights() throws Exception {
		mvc.perform(get("/guest/checkRights").sessionAttr("user",getGuest(1l))).andExpect(status().isOk());
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		Guest guest = getGuest(5l);
		 mvc.perform(put("/guest/{id}",guest.getId())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(guest)))
	                .andExpect(status().isOk());
	}
	
	
	
	@Test
	public void testAllGuests() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/guest").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		//.andExpect(jsonPath("$",hasSize(7)));
	}
	
	
	@Test
	public void activateGuestTest() throws Exception {
		 mvc.perform(put("/guest/activate/12345").session(session)).
		 andExpect(status().isOk());
	}
	
	
	
	
	
	
	private Guest getGuest(Long id) {
		Guest guest = new Guest();	
		guest.setId(id);
		guest.setFirstname("Petar");
		guest.setLastname("Peric");
		guest.setMail("pera@gmail.com");
		guest.setPassword("pera");
		guest.setRegistrated("1");
		return guest;
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
