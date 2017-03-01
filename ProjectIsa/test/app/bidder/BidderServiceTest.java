package app.bidder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.restaurant.restaurantOrder.RestaurantOrderRepository;
import app.restaurant.restaurantOrder.RestaurantOrderr;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidderServiceTest {
	
	@Autowired
	private BidderRepository repository;
	
	@Autowired
	private RestaurantOrderRepository restaurantOrderRepository;
	
	@Autowired
	private BidderService bidderService;

	@Test
	public void findAllTest() {
		List<Bidder> bidders = bidderService.findAll();
		assertThat(bidders.size()).isGreaterThan(4);
	}
	
	@Test
	public void saveTest() {
		Bidder bidderForsave = getBidder();
		Bidder bidder =  repository.save(bidderForsave);
		assertEquals(bidder.getMail(),bidderForsave.getMail());
	}

	@Test
	public void findOneByIdTest() {
		Bidder bidder =  repository.findOne(1l);
		assertNotNull(bidder);
	}

	@Test
	public void deleteTest() {
		repository.delete(8l);
		assertNull(repository.findOne(8l));
	}

	@Test
	public void findOneByPassAndMailTest() {
		String mail = "bidder7@gmail.com";
		String password = "1";
		Bidder bidder =  repository.findByMailAndPassword(mail,password);
		assertEquals(bidder.getMail(),mail);
		assertEquals(bidder.getPassword(),password);

	}

	@Test
	public void findOneWithMailTest() {
		String mail = "6";
		Bidder bidder =  repository.findByMail(mail);
		assertEquals(bidder.getMail(),mail);
	}
	
	@Test
	public void selectAllOffersWhereBidderCompetedTest() {
		ArrayList<RestaurantOrderr> restaurantOrderrs = bidderService.selectAllOffersWhereBidderCompeted(bidderService.findOne(1l));
		assertThat(restaurantOrderrs.size()).isGreaterThan(1);
	}
	
	@Test
	public void tryToChangeValueOfOfferTest() {
		boolean bool = bidderService.tryToChangeValueOfOffer(restaurantOrderRepository.findOne(1l), 1l);
		assertEquals(bool, false);
	}
	
	
	private Bidder getBidder() {
		Bidder bidder = new Bidder();	
		bidder.setFirstname("aaa");
		bidder.setLastname("bbb");
		bidder.setMail("1@1");
		bidder.setPassword("ppp");
		bidder.setRegistrated("1");
		return bidder;
	}	
}
