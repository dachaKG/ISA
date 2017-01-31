package app.bidder;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.offer.Offer;
import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;
import app.restaurant.restaurantOrder.RestaurantOrderr;

@RestController
@RequestMapping("/bidder")
public class BidderController {
	private final BidderService bidderService;
	private final RestaurantService restaurantService;
	private HttpSession httpSession;

	@Autowired
	public BidderController(final HttpSession httpSession, final BidderService bidderService,final RestaurantService restaurantService) {
		this.bidderService = bidderService;
		this.restaurantService = restaurantService;
		this.httpSession = httpSession;
	}

	@SuppressWarnings("unused")
	@GetMapping("/checkRights")
	public boolean checkRights() {
		try {
			Bidder bidder = ((Bidder) httpSession.getAttribute("user"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Bidder findAllLastOrders() {
		Long bidderId = ((Bidder) httpSession.getAttribute("user")).getId();
		Bidder bidder = bidderService.findOne(bidderId);
		return bidder;
	}
	
	@GetMapping("/getOffers")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<RestaurantOrderr> getOffers() {
		Bidder bidder = ((Bidder) httpSession.getAttribute("user"));
		ArrayList<RestaurantOrderr> restaurantOrderrs = new ArrayList<>();
		for(long i =1;i<restaurantService.findAll().size()+1;i++) {
			Restaurant restaurant = restaurantService.findOne(i);
			for(int j = 0;j<restaurant.getRestaurantOrders().size();j++) {
				RestaurantOrderr restaurantOrderr = restaurant.getRestaurantOrders().get(j);
				for(int q =0;q<restaurantOrderr.getOffers().size();q++) {
					Offer offer = restaurantOrderr.getOffers().get(q);
					if(offer.getBidder().getId() == bidder.getId()) {
						restaurantOrderrs.add((restaurantService.findOne(i).getRestaurantOrders().get(j)));
					}
				}
			}	
		}
		return restaurantOrderrs;
	}	
}