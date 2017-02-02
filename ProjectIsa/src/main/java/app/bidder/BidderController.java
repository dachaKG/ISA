package app.bidder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.offer.Offer;
import app.offer.OfferService;
import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;
import app.restaurant.restaurantOrder.RestaurantOrderService;
import app.restaurant.restaurantOrder.RestaurantOrderr;

@RestController
@RequestMapping("/bidder")
public class BidderController {
	private final BidderService bidderService;
	private final RestaurantOrderService restaurantOrderrService;
	private final RestaurantService restaurantService;
	private final OfferService offerService;
	private HttpSession httpSession;

	@Autowired
	public BidderController(final HttpSession httpSession, final BidderService bidderService,
			final RestaurantService restaurantService, final RestaurantOrderService restaurantOrderrService,final OfferService offerService) {
		this.bidderService = bidderService;
		this.restaurantService = restaurantService;
		this.restaurantOrderrService = restaurantOrderrService;
		this.offerService = offerService;
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

	// izlistavanje svih ponuda za logovanog ponudjaca
	@GetMapping("/getOffers")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<RestaurantOrderr> getOffers() {
		Bidder bidder = ((Bidder) httpSession.getAttribute("user"));
		ArrayList<RestaurantOrderr> restaurantOrderrs = new ArrayList<>();
		for (long i = 1; i < restaurantService.findAll().size() + 1; i++) {
			Restaurant restaurant = restaurantService.findOne(i);
			for (int j = 0; j < restaurant.getRestaurantOrders().size(); j++) {
				RestaurantOrderr restaurantOrderr = restaurant.getRestaurantOrders().get(j);
				for (int q = 0; q < restaurantOrderr.getOffers().size(); q++) {
					Offer offer = restaurantOrderr.getOffers().get(q);
					if (offer.getBidder().getId() == bidder.getId()) {
						restaurantOrderrs.add((restaurantService.findOne(i).getRestaurantOrders().get(j)));
					}
				}
			}
		}
		return restaurantOrderrs;
	}

	// izlistavanje svih ponuda za logovanog ponudjaca od svih restorana gde
	// moze da konkurise
	@GetMapping("/getActiveOffers")
	@ResponseStatus(HttpStatus.OK)
	public List<RestaurantOrderr> getActiveOffers() {
		Bidder bidder = ((Bidder) httpSession.getAttribute("user"));
		ArrayList<RestaurantOrderr> restaurantOrderrs = new ArrayList<>();
		for (long i = 1; i < restaurantService.findAll().size() + 1; i++) {
			Restaurant restaurant = restaurantService.findOne(i);
			for (int j = 0; j < restaurant.getBidders().size(); j++) {
				if (restaurant.getBidders().get(j).getId() == bidder.getId()) {
					restaurantOrderrs.addAll(restaurant.getRestaurantOrders());
				}
			}
		}
		return restaurantOrderrs;
	}

	// izmena vrednosti aktivne ponude
	@PostMapping("/changeValueOfPrice")
	@ResponseStatus(HttpStatus.OK)
	public int changeValueOfPrice(@Valid @RequestBody Offer offer) {
		List<RestaurantOrderr> restaurantOrderrs = restaurantOrderrService.findAll();
		for (int i = 0; i < restaurantOrderrs.size(); i++) {
			for (int j = 0; j < restaurantOrderrs.get(i).getOffers().size(); j++) {
				if (restaurantOrderrs.get(i).getOffers().get(j).getId() == offer.getId()) {
					Date currentDate = new Date();
					if (restaurantOrderrs.get(i).getEndDate().getTime() > currentDate.getTime()
							&& restaurantOrderrs.get(i).getOrderActive().equals("open")) {
						restaurantOrderrs.get(i).getOffers().get(j)
								.setPrice(Integer.parseInt(offer.getBidder().getRegistrated()));
						restaurantOrderrService.save(restaurantOrderrs.get(i));
						return 1;
					}
				}
			}
		}
		return 0;
	}

	// izmena vrednosti aktivne ponude
	@PostMapping("/competeWithInsertedValue")
	@ResponseStatus(HttpStatus.OK)
	public int competeWithInsertedValue(/*@Valid*/ @RequestBody RestaurantOrderr restaurantOrderr) {
		Long bidderId = ((Bidder) httpSession.getAttribute("user")).getId();
		Bidder bidder = bidderService.findOne(bidderId);
		List<RestaurantOrderr> restaurantOrderrs = restaurantOrderrService.findAll();
		for (int i = 0; i < restaurantOrderrs.size(); i++) {
			if (restaurantOrderrs.get(i).getId() == restaurantOrderr.getId()) {
				for(int q = 0;q<restaurantOrderrs.get(i).getOffers().size();q++) {
					if(restaurantOrderrs.get(i).getOffers().get(q).getBidder().getId() == bidderId)
						return 0;
				}
				Date currentDate = new Date();
				if (restaurantOrderrs.get(i).getEndDate().getTime() > currentDate.getTime()
						&& restaurantOrderrs.get(i).getOrderActive().equals("open")) {
					Offer offer = new Offer();
					offer.setPrice((int) (long) restaurantOrderr.getIdFromChoosenBidder());
					restaurantOrderr.setIdFromChoosenBidder(null);
					offer.setAccepted("in progress");
					offer.setBidder(bidder);
					offerService.save(offer);
					restaurantOrderr.getOffers().add(offer);
					restaurantOrderrService.save(restaurantOrderr);
					return 1;
				}
			}
		}
		return 0;
	}

}