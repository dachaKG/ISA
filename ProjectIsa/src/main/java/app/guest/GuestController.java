package app.guest;

import java.util.ArrayList;
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
import app.drink.Drink;
import app.drink.DrinkService;
import app.employed.waiter.Waiter;
import app.employed.waiter.WaiterService;
import app.order.OrderService;
import app.order.Orderr;
import app.reservation.Reservation;
import app.reservation.ReservationService;
import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;
import app.restaurant.Table;
import app.restaurant.TableService;

@RestController
@RequestMapping("/guest")
public class GuestController {

	private final GuestService guestService;
	private final RestaurantService restaurantService;
	private final WaiterService waiterService;

	private final DishService dishService;
	private final DrinkService drinkService;
	private final OrderService orderService;
	private Orderr order = new Orderr();
	
	private ArrayList<Guest> guests = new ArrayList<Guest>();
	
	private final TableService tableService;
	private final ReservationService reservationService;
	private HttpSession httpSession;
	

	@Autowired
	public GuestController(final HttpSession httpSession, final GuestService service, final RestaurantService restaurantService,
			DishService dishService,final DrinkService drinkService,
			final OrderService orderService, final TableService tableService, final ReservationService reservationService,
			final WaiterService waiterService) {
		this.guestService = service;
		this.httpSession = httpSession;
		this.restaurantService = restaurantService;
		this.dishService = dishService;
		this.drinkService = drinkService;
		this.orderService = orderService;
		this.tableService = tableService;
		this.reservationService = reservationService;
		this.waiterService = waiterService;
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
		return new ResponseEntity<>(guestService.findAll(), HttpStatus.OK);
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
		Optional.ofNullable(guestService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		guest.setId(id);
		return guestService.save(guest);
	}

	// kada se klikne na link iz maila
	@PutMapping(path = "/activate/{acNum}")
	@ResponseStatus(HttpStatus.OK)
	public void activateGuest(@PathVariable String acNum) {
		guestService.activate(acNum);
	}

	@GetMapping(path = "/findByFirstAndLastName/{inputStr}")
	public List<Guest> findByFirstAndLastName(@PathVariable String inputStr) {
		List<Guest> result = guestService.findByFirstAndLastName(inputStr);
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
	
	@GetMapping(path = "/removeDish/{id}")
	public Orderr removeDish(@PathVariable Long id){
		//da li treba zastita ovde sa ifom za size hrane?
		for(int i = 0 ; i < this.order.getFood().size(); i++){
			if(this.order.getFood().get(i).getId() == id){
				this.order.getFood().remove(i);
				break;
			}
		}
		
		return this.order;
	}
	
	@PutMapping(path = "/addDrink/{id}")
	public Orderr guestAddDrink(@PathVariable Long id){
		Drink drink = drinkService.findOne(id);
		this.order.getDrinks().add(drink);
		return this.order;
	}
	
	@PostMapping(path = "/makeOrder/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void makeOrder(@PathVariable Long id, @Valid @RequestBody Orderr order){
		if(order.getDrinks().size() > 0 || order.getFood().size() > 0){
			Table table = tableService.findOne(id);
			table.getReservations();
			order.setId(null);
			order.setTable(tableService.findOne(id));
			order.setTotal(order.getTotal());
			orderService.save(order);
			Restaurant restaurant = new Restaurant();
			//----------------------------
			//obrisati ovaj deo samo meni da olaksa
			List<Restaurant> rest = restaurantService.findAll();
			for(int i = 0 ; i < rest.size(); i++){
				for(int j = 0 ; j < rest.get(i).getSegments().size(); j++){
					for(int k = 0 ; k < rest.get(i).getSegments().get(j).getTables().size(); k++){
						if(rest.get(i).getSegments().get(j).getTables().get(k).getId() == id){
							restaurant = rest.get(i);
						}
					}
				}
			}
			
			restaurant.getOrder().add(order);
			Waiter waiter = waiterService.findOne((long) 1);
			waiter.getOrders().add(order);
			waiterService.save(waiter);
			restaurantService.save(restaurant);
			
			
			//-----------------------------
			//TO DO: dodati goste za rezervaciju
			this.order = new Orderr();
		}
	}
	
	@GetMapping(path="/restaurant/getTables/{id}")
	public List<app.restaurant.Table> getTables(@PathVariable Long id){
		Restaurant restaurant = restaurantService.findOne(id);
		ArrayList<app.restaurant.Table> outTables = new ArrayList<app.restaurant.Table>();
		for(int i=0; i<restaurant.getSegments().size(); i++){
			outTables.addAll(restaurant.getSegments().get(i).getTables());
		}
		return outTables;
	}
	
	
	@PostMapping(path="/makeReservation/{id}")
	public void makeReservation(@PathVariable Long id, @RequestBody Reservation reservation){
		//Guest guest = ((Guest) httpSession.getAttribute("user"));
		System.out.println("SUCCESS, id:"+id);
		System.out.println("DATE: "+reservation.getDate()+" h:"+reservation.getHours()+" m:"+reservation.getMinutes());
		Table table = tableService.findOne(id);
		table.getReservations().add(reservation);
		//reservation.getGuests().add(guestService.findOne(guest.getId()));
		reservationService.save(reservation);
	}
	
	
	
	
}