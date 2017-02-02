package app.bidder;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;
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
			final RestaurantService restaurantService, final RestaurantOrderService restaurantOrderrService,
			final OfferService offerService) {
		this.bidderService = bidderService;
		this.restaurantService = restaurantService;
		this.restaurantOrderrService = restaurantOrderrService;
		this.offerService = offerService;
		this.httpSession = httpSession;
	}

	@GetMapping("/checkRights")
	@ResponseStatus(HttpStatus.OK)
	public Bidder checkRights() throws AuthenticationException {
		try {
			return ((Bidder) httpSession.getAttribute("user"));
		} catch (Exception e) {
			throw new AuthenticationException("Forbidden.");
		}
	}

	// izlistavanje svih ponuda na koje je do sada konkurisao logovani ponudjac
	@GetMapping("/getOffers")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<RestaurantOrderr> getOffers() {
		Bidder bidder = ((Bidder) httpSession.getAttribute("user"));
		ArrayList<RestaurantOrderr> restaurantOrderrs = new ArrayList<>();
		List<Restaurant> restaurants = restaurantService.findAll();
		for (int i = 0; i < restaurants.size(); i++) {
			Restaurant restaurant = restaurants.get(i);
			for (int j = 0; j < restaurant.getRestaurantOrders().size(); j++) {
				RestaurantOrderr restaurantOrderr = restaurant.getRestaurantOrders().get(j);
				for (int q = 0; q < restaurantOrderr.getOffers().size(); q++) {
					Offer offer = restaurantOrderr.getOffers().get(q);
					if (offer.getBidder().getId() == bidder.getId()) {
						restaurantOrderrs.add((restaurants.get(i).getRestaurantOrders().get(j)));
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
		ArrayList<RestaurantOrderr> restaurantOrderrs = bidderService.selectAllOffersWhereBidderCompeted(bidder);
		return restaurantOrderrs;
	}

	// izmena vrednosti aktivne ponude
	@PostMapping("/changeValueOfPrice")
	@ResponseStatus(HttpStatus.OK)
	public void changeValueOfPrice(@Valid @RequestBody Offer offer) {
		List<RestaurantOrderr> restaurantOrderrs = restaurantOrderrService.findAll();
		for (int i = 0; i < restaurantOrderrs.size(); i++) {
			RestaurantOrderr restaurantOrder = restaurantOrderrs.get(i);
			List<Offer> listOfOffers = restaurantOrder.getOffers();
			for (int j = 0; j < listOfOffers.size(); j++) {
				if (listOfOffers.get(j).getId() == offer.getId()) {
					if (restaurantOrder.getEndDate().getTime() > restaurantOrder.getStartDate().getTime()
							&& restaurantOrder.getOrderActive().equals("open")) {
						listOfOffers.get(j).setPrice(Long.parseLong(offer.getBidder().getRegistrated()));
						restaurantOrderrService.save(restaurantOrder);
						return;
					}
				}
			}
		}
		throw new IllegalArgumentException();
	}

	// izmena vrednosti aktivne ponude
	@PostMapping("/competeWithInsertedValue")
	@ResponseStatus(HttpStatus.OK)
	public void competeWithInsertedValue(/* @Valid */ @RequestBody RestaurantOrderr restaurantOrderr) {
		Long bidderId = ((Bidder) httpSession.getAttribute("user")).getId();
		Bidder bidder = bidderService.findOne(bidderId);
		if (bidderService.tryToChangeValueOfOffer(restaurantOrderr, bidderId, bidder)) {
			Offer offer = setOffer(restaurantOrderr.getIdFromChoosenBidder(), bidder);
			restaurantOrderr.setIdFromChoosenBidder(null);
			offerService.save(offer);
			restaurantOrderr.getOffers().add(offer);
			restaurantOrderrService.save(restaurantOrderr);
			return;
		}
		throw new RuntimeException("Can't make changes.");
	}

	private Offer setOffer(Long price, Bidder bidder) {
		Offer offer = new Offer();
		offer.setPrice(price);
		offer.setAccepted("in progress");
		offer.setBidder(bidder);
		return offer;
	}
}