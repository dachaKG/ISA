package app.guest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.dish.Dish;
import app.dish.DishService;
import app.order.OrderService;
import app.order.Orderr;
import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;

@RestController
@RequestMapping("/guest")
public class GuestController {

	private final GuestService service;
	private final RestaurantService restaurantService;
	private final DishService dishService;
	private final OrderService orderService;
	private Orderr order = new Orderr();
	private HttpSession httpSession;

	@Autowired
	public GuestController(final HttpSession httpSession, final GuestService service, final RestaurantService restaurantService,
			DishService dishService,final OrderService orderService) {
		this.service = service;
		this.httpSession = httpSession;
		this.restaurantService = restaurantService;
		this.dishService = dishService;
		this.orderService = orderService;
	}

	@SuppressWarnings("unused")
	@GetMapping("/checkRights")
	public boolean checkRights() {
		try {
			Guest guest = ((Guest) httpSession.getAttribute("user"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// izlistavanje svih gostiju
	@GetMapping
	public ResponseEntity<List<Guest>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/restaurants")
	public ResponseEntity<List<Restaurant>> findAllRestaurants() {
		return new ResponseEntity<>(restaurantService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/restaurant/{id}")
	public Restaurant findRestaurant(@PathVariable Long id){
		Optional.ofNullable(restaurantService.findOne(id))
					.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		Restaurant r = restaurantService.findOne(id);
		return r;
		
	}

	// 2.2
	// izmena informacija o gostu
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Guest update(@PathVariable Long id, @Valid @RequestBody Guest guest) {
		Optional.ofNullable(service.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		guest.setId(id);
		return service.save(guest);
	}

	// kada se klikne na link iz maila
	@PutMapping(path = "/activate/{acNum}")
	@ResponseStatus(HttpStatus.OK)
	public void activateGuest(@PathVariable String acNum) {
		service.activate(acNum);
	}

	@GetMapping(path = "/findByFirstAndLastName/{inputStr}")
	public List<Guest> findByFirstAndLastName(@PathVariable String inputStr) {
		List<Guest> result = service.findByFirstAndLastName(inputStr);
		//izbacivanje njega samog iz liste
		result.remove((Guest) httpSession.getAttribute("user"));
		// treba izbaciti i kad postoji vec prijateljstvo...
		
		return result;
	}
	
	@GetMapping(path = "/order")
	public Orderr guestOrder(){
		Orderr order = this.order;
		return order;
	}
	
	@PutMapping(path = "/addDish/{id}")
	public Orderr guestAddDish(@PathVariable Long id){
		Dish dish = dishService.findOne(id);
		this.order.getFood().add(dish);
		return this.order;
	}
	
	@PostMapping(path = "/makeOrder")
	@ResponseStatus(HttpStatus.CREATED)
	public void makeOrder(@Valid @RequestBody Orderr order){
		order.setId(null);
		orderService.save(order);
		this.order = new Orderr();
	}
	
	
}