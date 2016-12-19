package app.manager.restaurant;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.drink.Drink;
import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;

@RestController
@RequestMapping("/restaurantManager")
public class RestaurantManagerController {

	private HttpSession httpSession;
	private RestaurantService restaurantService;

	@Autowired
	public RestaurantManagerController(final HttpSession httpSession, final RestaurantService restaurantService) {
		this.httpSession = httpSession;
		this.restaurantService = restaurantService;
	}

	@GetMapping("/checkRights")
	public boolean checkRights() {
		try {
			RestaurantManager restaurantManager = ((RestaurantManager) httpSession.getAttribute("user"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@GetMapping("/restaurant")
	public ResponseEntity<Restaurant> findManager() {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}

	@PostMapping(path = "/restaurant/saveDrink")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveDrink(@Valid @RequestBody Drink drink) {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		restaurant.getDrinks().add(drink);
		restaurantService.save(restaurant);
	}
}