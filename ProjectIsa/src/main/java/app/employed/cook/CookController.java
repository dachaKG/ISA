package app.employed.cook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.dish.Dish;
import app.employed.DefaultShift;
import app.employed.waiter.Waiter;
import app.order.FoodStatus;
import app.order.OrderService;
import app.order.Orderr;
import app.reservation.Reservation;
import app.reservation.ReservationService;
import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;

@RestController
@RequestMapping("/cook")
public class CookController {
	HttpSession httpSession;

	private final CookService cookService;
	private final OrderService orderService;
	private final CookOrderService cookOrderService;
	private final ReservationService reservationService;
	private final RestaurantService restaurantService;

	@Autowired
	public CookController(final HttpSession httpSession, final CookService cookService, final OrderService orderService,
			final CookOrderService cookOrderService, final ReservationService reservationService, final RestaurantService restaurantService) {
		this.httpSession = httpSession;
		this.cookService = cookService;
		this.orderService = orderService;
		this.cookOrderService = cookOrderService;
		this.reservationService = reservationService;
		this.restaurantService = restaurantService;
		
	}

	@SuppressWarnings("unused")
	@GetMapping("/checkRights")
	public boolean checkRights() {
		try {
			Cook cook = ((Cook) httpSession.getAttribute("user"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Cook findCook() {
		Long id = ((Cook) httpSession.getAttribute("user")).getId();
		Cook cook = cookService.findOne(id);
		return cook;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Cook cook) {
		cook.setId(null);
		cook.setRegistrated("0");
		cookService.save(cook);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cook findOne(@PathVariable Long id) {
		Cook cook = cookService.findOne(id);
		Optional.ofNullable(cook).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return cook;
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		cookService.delete(id);
	}

	// 2.4. kuvar ima mogucnost da azurira podatke
	@PutMapping(path = "/profile/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cook update(@PathVariable Long id, @Valid @RequestBody Cook cook) {
		Optional.ofNullable(cookService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		cook.setId(id);
		return cookService.save(cook);
	}
	
	@PutMapping(path = "/changePassword/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cook changePassword(@PathVariable Long id, @Valid @RequestBody Cook cook) {
		Optional.ofNullable(cookService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		cook.setId(id);
		return cookService.save(cook);
	}

	// 2.4 vidi listu porudzbina jela koje je potrebno pripremiti
	@GetMapping(path = "/orders")
	public ResponseEntity<List<Orderr>> findAllOrdrers() {
		Long id = ((Cook) httpSession.getAttribute("user")).getId();
		Cook cook = cookService.findOne(id);

		List<Orderr> orders = new ArrayList<Orderr>();
		List<Orderr> allOrders = orderService.findAll();
		List<Orderr> tempOrders = new ArrayList<Orderr>();
		for (int i = 0; i < allOrders.size(); i++) {
			if (allOrders.get(i).getFood().size() != 0 && allOrders.get(i).getFoodStatus() != null
					&& allOrders.get(i).getFoodStatus().compareTo(FoodStatus.inPrepared) == 0 ) {
				tempOrders.add(allOrders.get(i));
			}
		}
		List<CookOrder> cookOrdersTemp = cookOrderService.findAll();
		List<CookOrder> cookOrders = new ArrayList<CookOrder>();
		for (int i = 0; i < cookOrdersTemp.size(); i++) {
			if (cookOrdersTemp.get(i).getCookId() == cook.getId()) {
				cookOrders.add(cookOrdersTemp.get(i));
			}
		}

		orders.addAll(tempOrders);
		/*List<Orderr> orders = new ArrayList<Orderr>();
		for (int i = 0; i < cookOrders.size(); i++) {
			for (int j = 0; j < tempOrders.size(); j++) {
				if (cook.getId() == cookOrders.get(i).getCookId()
						&& tempOrders.get(j).getId() != cookOrders.get(i).getOrderId()) {
					orders.add(tempOrders.get(j));
				}
			}
		}*/
		List<Orderr> orderFood = new ArrayList<Orderr>();

		for (int i = 0; i < orders.size(); i++) {
			List<Dish> food = new ArrayList<Dish>();
			for (int j = 0; j < orders.get(i).getFood().size(); j++) {
				if (orders.get(i).getFood().get(j).getTypeOfDish().toString()
						.equals(cook.getTypeOfCooker().toString())) {
					food.add(orders.get(i).getFood().get(j));
				}
			}
			orders.get(i).setFood(food);
			if (orders.get(i).getFood().size() > 0) {
				orderFood.add(orders.get(i));
			}
		}
		
		List<Orderr> returnOrderFood = new ArrayList<Orderr>(); 
		List<Reservation> reservationsTemp = new ArrayList<Reservation>();
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		
		//danasnje rezervacije
		for(int i = 0 ; i < reservationService.findAll().size(); i++){
			if(reservationService.findAll().get(i).getDate().toString().equals(ft.format(date))){
				reservationsTemp.add(reservationService.findAll().get(i));
			}
		}
		
		//provera smene
		List<Reservation> reservations = getActiveReservations(reservationsTemp, cook);
		
		
		
		//provera  da li se narudzbiina poklapa sa narudzbinom rezervacije
		for(int i = 0 ; i < reservations.size(); i++){
			for(int k = 0 ; k < reservations.get(i).getOrders().size(); k++){
				for(int j = 0 ; j < orderFood.size(); j++){
					if(reservations.get(i).getOrders().get(k).getId() == orderFood.get(j).getId()){
						returnOrderFood.add(orderFood.get(j));
					}
				}
			}
		}
		
		
		

		return new ResponseEntity<>(returnOrderFood, HttpStatus.OK);
	}

	@GetMapping(path = "/foodReceived/{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public Orderr foodReceived(@PathVariable Long orderId) {
		Long id = ((Cook) httpSession.getAttribute("user")).getId();
		Cook cook = cookService.findOne(id);
		Optional.ofNullable(cook).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		Optional.ofNullable(orderService.findOne(orderId))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		Orderr order = orderService.findOne(orderId);

		CookOrder cookOrder = new CookOrder();
		cookOrder.setOrder(order);
		cookOrder.setCook(cook);
		cookOrder.setDishStatus(DishStatus.received);
		cookOrder.setCookId(cook.getId());
		cookOrder.setOrderId(order.getId());
		cookOrderService.save(cookOrder);

		return order;
	}

	@GetMapping(path = "/receivedFood")
	public ResponseEntity<List<Orderr>> receivedFood() {
		Long id = ((Cook) httpSession.getAttribute("user")).getId();
		Cook cook = cookService.findOne(id);

		List<CookOrder> cookService = cookOrderService.findAll();
		List<Orderr> orders = new ArrayList<Orderr>();
		for (int i = 0; i < cookService.size(); i++) {
			if (cookService.get(i).getCook().getId() == cook.getId()
					&& cookService.get(i).getDishStatus().compareTo(DishStatus.received) == 0) {
				orders.add(cookService.get(i).getOrder());
			}
		}
		List<Dish> food = new ArrayList<Dish>();
		for (int i = 0; i < orders.size(); i++) {
			for (int j = 0; j < orders.get(i).getFood().size(); j++) {
				if (orders.get(i).getFood().get(j).getTypeOfDish().toString()
						.equals(cook.getTypeOfCooker().toString())) {
					food.add(orders.get(i).getFood().get(j));
				}
			}
			if (food.size() > 0) {
				orders.get(i).setFood(food);
			}
		}

		return new ResponseEntity<>(orders, HttpStatus.OK);

	}

	// 2.4 signazilira da je odgovarajuca narudzbina jela gotova
	@GetMapping(path = "/foodReady/{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public Orderr foodReady(@PathVariable Long orderId) {

		Long id = ((Cook) httpSession.getAttribute("user")).getId();
		Cook cook = cookService.findOne(id);
		Optional.ofNullable(cook).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		Optional.ofNullable(orderService.findOne(orderId))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		Orderr order = orderService.findOne(orderId);

		CookOrder cookOrder = new CookOrder();
		cookOrder.setOrder(order);
		cookOrder.setCook(cook);
		cookOrder.setDishStatus(DishStatus.finished);
		cookOrder.setCookId(cook.getId());
		cookOrder.setOrderId(order.getId());
		cookOrderService.save(cookOrder);
		List<Dish> foodTemp = new ArrayList<Dish>();
		for (int i = 0; i < order.getFood().size(); i++) {
			if (!order.getFood().get(i).getTypeOfDish().toString().equals(cook.getTypeOfCooker().toString())) {
				foodTemp.add(order.getFood().get(i));
			}
		}
		List<Dish> foodTypeOne = new ArrayList<Dish>();
		List<Dish> foodTypeTwo = new ArrayList<Dish>();
		if (foodTemp.size() > 0) {
			for (int i = 0; i < foodTemp.size(); i++) {
				if (i == 0) {
					foodTypeOne.add(foodTemp.get(i));
				} else {
					if (!foodTemp.get(i).getTypeOfDish().toString()
							.equals(foodTypeOne.get(0).getTypeOfDish().toString())) {
						foodTypeTwo.add(foodTemp.get(i));
					} else {
						foodTypeOne.add(foodTemp.get(i));
					}
				}

			}
		}
		
		int numberOfTypeDish = 1;
		if(foodTypeOne.size() > 0){
			numberOfTypeDish++;
		}
		if(foodTypeTwo.size() > 0){
			numberOfTypeDish++;
		}
		
		List<CookOrder> numberOfCookOrders = new ArrayList<CookOrder>();
		for(int i = 0 ; i < cookOrderService.findAll().size(); i++){
			if(cookOrderService.findAll().get(i).getOrderId() == order.getId()){
				numberOfCookOrders.add(cookOrderService.findAll().get(i));
			}
		}
		
		boolean finishedFood = false;
		if(numberOfCookOrders.size() == numberOfTypeDish){
			for(int i = 0; i < numberOfCookOrders.size(); i++){
				if(numberOfCookOrders.get(i).getDishStatus().compareTo(DishStatus.finished) == 0){
					finishedFood = true;
				} else {
					finishedFood = false;
					break;
				}
			}
		}
		
		if(finishedFood){
			order.setFoodStatus(FoodStatus.finished);
			orderService.save(order);
		}
		
		return order;

	}
	
	public List<Reservation> getActiveReservations(List<Reservation> reservations, Cook cook){
		
		List<Reservation> returnReservation = new ArrayList<Reservation>();
		Date date = new Date();
		SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
		String timeString = date24Format.format(date);
		try {
			date = date24Format.parse(timeString);
			for (int i = 0; i < reservations.size(); i++) {
				String time = "", timeEnd = "";
				time += "" + reservations.get(i).getHours() + ":" + reservations.get(i).getMinutes();
				timeEnd += "" + (reservations.get(i).getHours() + reservations.get(i).getDuration().intValue()) + ":"
						+ reservations.get(i).getMinutes();
				Date timeDate = new Date();
				Date timeDateEnd = new Date();
				Date shiftTime = new Date();
				timeDate = date24Format.parse(time);
				timeDateEnd = date24Format.parse(timeEnd);
				shiftTime = date24Format.parse("16:00");
				
				if (date.after(timeDate) && date.before(timeDateEnd)) {
					if(cook.getDefaultShift().compareTo(DefaultShift.First) == 0){
						if(date.before(shiftTime)){
							returnReservation.add(reservations.get(i));
						}
					} else {
						if(date.after(shiftTime)){
							returnReservation.add(reservations.get(i));
						}
					}
				}
			}
			return returnReservation;

			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnReservation;
	}


}
