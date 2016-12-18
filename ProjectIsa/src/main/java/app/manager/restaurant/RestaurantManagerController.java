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
	private RestaurantManagerService restaurantManagerService;
	private RestaurantService restaurantService;
	@Autowired
	public RestaurantManagerController(final HttpSession httpSession,final RestaurantManagerService restaurantManagerService,final RestaurantService restaurantService) {
		this.httpSession = httpSession;
		this.restaurantManagerService = restaurantManagerService;
		this.restaurantService = restaurantService;
	}
	
	@GetMapping("/restaurant")
	public ResponseEntity<Restaurant> findManager() {
		//return new ResponseEntity<>(((RestaurantManager)httpSession.getAttribute("logovan")).getRestaurant(), HttpStatus.OK);
	
		//ovo kasnije leti
		httpSession.setAttribute("logovan", restaurantManagerService.findOne(1L));	
		return new ResponseEntity<>(restaurantManagerService.findOne(1l).getRestaurant(), HttpStatus.OK);
	}
	
	@PostMapping(path = "/restaurant/saveDrink")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveDrink(@Valid @RequestBody Drink drink) {
		Restaurant restaurant = ((RestaurantManager)httpSession.getAttribute("logovan")).getRestaurant();
		
		restaurant.getDrinks().add(drink);
		//restaurantManagerService.save(restaurantManagerService.findOne(1l));
		restaurantService.save(restaurant);
	}
	
	
}