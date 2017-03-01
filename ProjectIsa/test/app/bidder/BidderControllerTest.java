package app.bidder;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Ignore;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import app.offer.Offer;
import app.restaurant.RestaurantService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidderControllerTest {

	@Autowired
	BidderController bidderController;
	
	@Autowired
	BidderService bidderService;
	
	@Autowired
	RestaurantService restaurantService;
	
	PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver();

	private MockMvc mvc;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(bidderController).setCustomArgumentResolvers(pageResolver)
				.build();
	}
	
	@Test
	public void updateBidderProfile() throws Exception {
		mvc.perform(get("/bidder/updateBidderProfile/{firstName}/{lastName}/{password}","asd","dsa","dsada").sessionAttr("user",getBidder(3l))).andExpect(status().isOk());
	}
	
	@Test
	public void checkRights() throws Exception {
		mvc.perform(get("/bidder/checkRights").sessionAttr("user",getBidder(1l))).andExpect(status().isOk());
	}
	
	
	@Test
	public void getActiveOffers() throws Exception {
		mvc.perform(get("/bidder/getActiveOffers").sessionAttr("user",getBidder(5l))).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@Ignore
	public void competeWithInsertedValue() throws Exception {
		MvcResult mock = mvc.perform(post("/bidder/competeWithInsertedValue/{id}",1l).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getOffer())).sessionAttr("user",getBidder(1l))).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		String content = mock.getResponse().getContentAsString();
		assertEquals(content, "no");
		
	}
	
	private Bidder getBidder(Long id) {
		Bidder bidder = new Bidder();
		bidder.setId(id);
		bidder.setFirstname("aaa");
		bidder.setLastname("bbb");
		bidder.setMail("bidder1@gmail.com");
		bidder.setPassword("1");
		bidder.setRegistrated("1");
		return bidder;
	}	
		
	
	private Offer getOffer() {
		Offer offer = new Offer();
		offer.setId(1l);
		offer.setPrice(2l);
		offer.setGaranty(10);
		offer.setPosibleDelivery(new Date(0));
		return offer;
	}	
	
	protected byte[] convertObjectToJsonBytes(final Object object) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		mapper.setDateFormat(dateFormat);
		return mapper.writeValueAsBytes(object);
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
