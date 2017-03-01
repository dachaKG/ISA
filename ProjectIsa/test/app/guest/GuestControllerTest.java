package app.guest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
		
	}

	@Test
	public void checkRights() throws Exception {
		mvc.perform(get("/guest/checkRights").sessionAttr("user",getGuest(1l))).andExpect(status().isOk());
	}
	
	@Ignore
	@Test
	public void testUpdate() throws Exception {
		Guest guest = getGuest(1l);
		 mvc.perform(put("/guest/{id}",guest.getId())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(guest)))
	                .andExpect(status().isOk());
	}
	
	@Test
	public void ActivateGuest() throws Exception {
		Guest guest = getGuest(1l);
		String acNum = "12345";
		mvc.perform(put("/guest/activate/{acNum}",acNum).sessionAttr("user",getGuest(1l))).andExpect(status().isOk());
	}
	
	private Guest getGuest(Long id) {
		Guest guest = new Guest();	
		guest.setId(id);
		guest.setFirstname("mika");
		guest.setLastname("mikic");
		guest.setMail("guest1");
		guest.setPassword("pass1");
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
