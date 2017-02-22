package app.employed.waiter;

import static org.mockito.Matchers.intThat;

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

import app.bill.Bill;
import app.bill.BillService;
import app.dish.Dish;
import app.drink.Drink;
import app.drink.DrinkService;
import app.employed.bartender.Bartender;
import app.employed.bartender.BartenderService;
import app.employed.cook.Cook;
import app.employed.cook.CookService;
import app.employed.cook.DishStatus;
import app.guest.Guest;
import app.order.FoodStatus;
import app.order.DrinkStatus;
import app.order.OrderService;
import app.order.Orderr;
import app.reservation.Reservation;
import app.reservation.ReservationService;
import app.restaurant.Table;
import app.restaurant.TableService;

@RestController
@RequestMapping("/waiter")
public class WaiterController {

	private HttpSession httpSession;

	private final WaiterService waiterService;
	private final OrderService orderService;
	private final BartenderService bartenderService;
	private final CookService cookService;
	private final TableService tableService;
	private final ReservationService reservationService;
	private final BillService billService;
	
	@Autowired
	public WaiterController(final HttpSession httpSession, final WaiterService service, final OrderService orderService,
			final DrinkService drinkService, final BartenderService bartenderService,final CookService cookService,
			final TableService tableService, final ReservationService reservationService, final BillService billService) {
		this.httpSession = httpSession;
		this.waiterService = service;
		this.orderService = orderService;
		this.bartenderService = bartenderService;
		this.cookService = cookService;
		this.tableService = tableService;
		this.reservationService = reservationService;
		this.billService = billService;
	}

	@SuppressWarnings("unused")
	@GetMapping("/checkRights")
	public boolean checkRights() {
		try {
			Waiter waiter = ((Waiter) httpSession.getAttribute("user"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@GetMapping
	public ResponseEntity<Waiter> findBartender() {
		Long id = ((Waiter) httpSession.getAttribute("user")).getId();
		Waiter waiter = waiterService.findOne(id);
		return new ResponseEntity<Waiter>(waiter, HttpStatus.OK);
	}

	@GetMapping(path = "/readyFood")
	public ResponseEntity<List<Dish>> readyFood() {
		Long id = ((Waiter) httpSession.getAttribute("user")).getId();
		Waiter waiter = waiterService.findOne(id);
		List<Orderr> orders = waiter.getOrders();

		Optional.ofNullable(orders).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		List<Dish> food = new ArrayList<Dish>();

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getFood().size() != 0 && orders.get(i).getFoodStatus() != null
					&& orders.get(i).getFoodStatus().compareTo(FoodStatus.finished) == 0) {
				for (int j = 0; j < orders.get(i).getFood().size(); j++) {
					food.add(orders.get(i).getFood().get(j));
				}
			}
		}
		return new ResponseEntity<>(food, HttpStatus.OK);

	}

	@GetMapping(path = "/readyDrinks")
	public ResponseEntity<List<Drink>> readyDrinks() {
		Long id = ((Waiter) httpSession.getAttribute("user")).getId();
		Waiter waiter = waiterService.findOne(id);
		List<Orderr> orders = waiter.getOrders();

		Optional.ofNullable(orders).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		List<Drink> drinks = new ArrayList<Drink>();

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getDrinks().size() != 0 && orders.get(i).getDrinkStatus() != null
					&& orders.get(i).getDrinkStatus().compareTo(DrinkStatus.finished) == 0) {
				for (int j = 0; j < orders.get(i).getDrinks().size(); j++) {
					drinks.add(orders.get(i).getDrinks().get(j));
				}
			}
		}

		return new ResponseEntity<>(drinks, HttpStatus.OK);
	}

	@GetMapping(path = "/readyOrders")
	public ResponseEntity<List<Orderr>> readyOrder() {
		Long id = ((Waiter) httpSession.getAttribute("user")).getId();
		Waiter waiter = waiterService.findOne(id);
		List<Orderr> orders = waiter.getOrders();

		Optional.ofNullable(orders).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		List<Orderr> finishedOrders = new ArrayList<Orderr>();
		List<Long> longs = new ArrayList<Long>();
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getDrinks().size() != 0 && orders.get(i).getDrinkStatus() != null
					&& orders.get(i).getDrinkStatus().compareTo(DrinkStatus.finished) == 0
					&& orders.get(i).getFood().size() != 0 && orders.get(i).getFoodStatus() != null && 
					orders.get(i).getFoodStatus().compareTo(FoodStatus.finished) == 0) {
				
				finishedOrders.add(orders.get(i));
				longs.add(orders.get(i).getId());
				
			}
		}
		
		

		return new ResponseEntity<>(finishedOrders, HttpStatus.OK);
	}
	
	@GetMapping(path = "/orders")
	public ResponseEntity<List<Orderr>> orders(){
		Long id = ((Waiter) httpSession.getAttribute("user")).getId();
		Waiter waiter = waiterService.findOne(id);
		List<Orderr> orders = waiter.getOrders();
		
		List<Orderr> orderss = new ArrayList<Orderr>();

		for(int i = 0 ; i < orders.size(); i++){
			if(orders.get(i).getDrinks().size() != 0 && orders.get(i).getDrinkStatus() == null &&
					orders.get(i).getFood().size() != 0 && orders.get(i).getFoodStatus() == null	){
				orderss.add(orders.get(i));
			}
		}
		
		return new ResponseEntity<>(orderss, HttpStatus.OK);
	}
	
	@PutMapping(path = "/sendToEmployed/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void sendToEmployed(@PathVariable Long id){
		
		Optional.ofNullable(orderService.findOne(id)).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		Orderr order = orderService.findOne(id);
		
		
		if(order.getDrinks().size() > 0 && order.getDrinkStatus() == null){
			order.setDrinkStatus(DrinkStatus.inPrepared);
		}
		
		if(order.getFood().size() > 0 && order.getFoodStatus() == null){
			order.setFoodStatus(FoodStatus.inPrepared);
		}
		
		orderService.save(order);
		
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Waiter waiter) {
		waiter.setId(null);
		waiter.setRegistrated("0");
		waiterService.save(waiter);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Waiter findOne(@PathVariable Long id) {
		Optional.ofNullable(waiterService.findOne(id)).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		
		Waiter waiter = waiterService.findOne(id);
		return waiter;
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Waiter update(@PathVariable Long id, @Valid @RequestBody Waiter waiter) {
		Optional.ofNullable(waiterService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		waiter.setId(id);
		return waiterService.save(waiter);
	}

	// sve narudzbine za odredjenog konobara
	@GetMapping(path = "/{id}/order")
	public ResponseEntity<List<Orderr>> findAllOrders(@PathVariable Long id) {
		Optional.ofNullable(waiterService.findOne(id).getOrders())
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		return new ResponseEntity<>(waiterService.findOne(id).getOrders(), HttpStatus.OK);

	}
	
	@PostMapping(path = "/makeBill")
	@ResponseStatus(HttpStatus.CREATED)
	public void makeBill(@Valid @RequestBody Orderr order){
		Long id = ((Waiter) httpSession.getAttribute("user")).getId();
		Waiter waiter = waiterService.findOne(id);
		Table table = tableService.findOne(order.getTable().getId());
		//Table table = order.getTable();
		List<Reservation> reservations = table.getReservations();
		Reservation reservation = new Reservation();
		for(int i = 0 ; i < reservations.size(); i++){
			for(int j = 0 ; j < reservations.get(i).getOrders().size(); j++){
				if(reservations.get(i).getOrders().get(j).getId() == order.getId()){
					reservation = reservations.get(i);
					break;
				}
			}
		}
		
		
		Bill bill = new Bill();
		bill.setDate(reservation.getDate());
		bill.setTotal(order.getTotal());
		bill.setId(null);
		bill.setReservation(reservation);
		billService.save(bill);
		waiter.getBills().add(bill);
		waiterService.save(waiter);
		
	}

	// 2.4. Konobar izmeni porudzbinu za odredjeni sto
	/*
	 * @PutMapping(path = "/{id}/order/{orderId}")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public Orderr update(@PathVariable("id")
	 * Long id, @PathVariable("orderId") Long orderId, @Valid @RequestBody
	 * Orderr order) { Optional.ofNullable(service.findOne(id)) .orElseThrow(()
	 * -> new ResourceNotFoundException("Resource Not Found!"));
	 * Optional.ofNullable(orderService.findOne(orderId)) .orElseThrow(() -> new
	 * ResourceNotFoundException("Resource Not Found!"));
	 * 
	 * order.setId(orderId); // orderService.findOne(id1).getTable(); //
	 * service.findOne(id).getSegment().getTables().
	 * 
	 * return orderService.save(order); }
	 */

	// 2.4 zavrsi porudzbinu i kreira racun
	/*
	 * @GetMapping(path = "/{id}/finishOrder/{id1}")
	 * //@ResponseStatus(HttpStatus.OK) public ResponseEntity<Orderr>
	 * finishTheOrder(@PathVariable Long id, @PathVariable Long
	 * id1, @Valid @RequestBody Orderr order) { order =
	 * orderService.findOne(id1); Optional.ofNullable(service.findOne(id))
	 * .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
	 * Optional.ofNullable(order) .orElseThrow(() -> new
	 * ResourceNotFoundException("Resource Not Found!"));
	 * 
	 * orderService.findOne(id1).setStatus(true);
	 * 
	 * return new ResponseEntity<>(orderService.findOne(id1), HttpStatus.OK);
	 * 
	 * }
	 */
}
