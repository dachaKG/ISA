package app.guest;

import static org.junit.Assert.*;
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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.hasSize;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.friends.FriendsController;
import app.friends.FriendsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendsControllerTest {
	
	@Autowired
	FriendsController friendsController;
	
	@Autowired
	FriendsService friendsService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mvc;
	
	protected MockHttpSession session;
	
	PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver();
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(friendsController).setCustomArgumentResolvers(pageResolver)
				.build();
		Guest g = getGuest(5l);
		session = new MockHttpSession();
		session.setAttribute("user", g);
	}

	@Test
	public void getAllFriendsOfLoggedUserTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/friends/list").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		//.andExpect(jsonPath("$",hasSize(0)));
	}
	
	@Test
	public void getAllFriends() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/friends")).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	@Test
	public void getAllRecivedRequestsTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/friends/recivedRequests").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		//.andExpect(jsonPath("$",hasSize(0)));
	}
	
	@Test
	public void addFriendTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/friends/addFriend/{id}", 3l).session(session)).andDo(print())
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
