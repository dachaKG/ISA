package app.common;

import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import app.guest.Guest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonControllerTest {
	
	@Autowired
	CommonController commonController;
	
	private MockMvc mvc;
	
	protected MockHttpSession session;
	
	PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver();
	
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(commonController).setCustomArgumentResolvers(pageResolver)
				.build();
		Guest g = getGuest(5l);
		session = new MockHttpSession();
		session.setAttribute("user", g);
		
	}

	
	@Test
	public void loginTest() throws Exception {
		mvc.perform(post("/commonController/logIn")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getGuest(5l))))
        	.andExpect(status().isOk());
	}
	
	@Test
	public void logoutTest() throws Exception {
		mvc.perform(get("/commonController/logOut")).andExpect(status().isOk());
	}
	
	@Test
	public void getLoggedUserTest() throws Exception {
		mvc.perform(get("/commonController/getLoggedUser").sessionAttr("user",getGuest(5l)))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk());
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
