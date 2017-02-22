/*package app.employed.cook;

import java.util.ArrayList;
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
import app.dish.DishStatus;
import app.order.FoodStatus;
import app.order.OrderService;
import app.order.Orderr;
import app.restaurant.Restaurant;

@RestController
@RequestMapping("/cook")
public class CookController {
	HttpSession httpSession;

	private final CookService cookService;
	private final OrderService orderService;

	@Autowired
	public CookController(final HttpSession httpSession, final CookService cookService,
			final OrderService orderService) {
		this.httpSession = httpSession;
		this.cookService = cookService;
		this.orderService = orderService;
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

	
	 * @GetMapping public ResponseEntity<List<Cook>> findAll() { return new
	 * ResponseEntity<>(cookService.findAll(), HttpStatus.OK); }
	 

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
		Restaurant restaurant = cookService.findOne(id).getRestaurant();
		Cook cook2 =  cookService.findOne(cook.getId());
		cook2.setRestaurant(restaurant);
		cook2.setId(id);
		return cookService.save(cook2);
	}

	// 2.4 vidi listu porudzbina jela koje je potrebno pripremiti
	@GetMapping(path = "/orders")
	public ResponseEntity<List<Orderr>> findAllOrdrers() {
		Long id = ((Cook) httpSession.getAttribute("user")).getId();
		// Bartender bartender = ((Bartender) httpSession.getAttribute("user"));
		Cook cook = cookService.findOne(id);
		
		//----------------------------------
		List<Orderr> orders = new ArrayList<Orderr>();
		List<Orderr> allOrders = orderService.findAll();
		for(int i = 0 ; i < allOrders.size(); i++){
			if(allOrders.get(i).getFood().size() != 0 && allOrders.get(i).getFoodStatus().compareTo(FoodStatus.inPrepared) == 0){
				orders.add(allOrders.get(i));
			}
		}
		
		List<Orderr> orderFood = new ArrayList<Orderr>();
		for(int i = 0 ; i < orders.size(); i++){
			List<Dish> food = new ArrayList<Dish>();
			for(int j = 0 ; j < orders.get(i).getFood().size(); j++){
				if(orders.get(i).getFood().get(j).getTypeOfDish().toString().equals(cook.getTypeOfCooker().toString()) && 
						orders.get(i).getFood().get(j).getDishStatus() == null){
					food.add(orders.get(i).getFood().get(j));
				}
			}
			orders.get(i).setFood(food);
			if(orders.get(i).getFood().size() > 0){
				cook.getOrders().addAll(orders);
				orderFood.add(orders.get(i));
			}
		}
		
		return new ResponseEntity<>(orderFood, HttpStatus.OK);
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
		
		for(int i = 0 ; i < order.getFood().size(); i++){
			if(order.getFood().get(i).getTypeOfDish().toString().equals(cook.getTypeOfCooker().toString())){
				cook.getOrders().add(order);
				break;
			}
		}
		
		for(int i = 0 ; i < cook.getOrders().size(); i++){
			if(cook.getOrders().get(i).getId() == orderId){
				for(int j = 0 ; j < cook.getOrders().get(i).getFood().size(); j++){
					if(cook.getOrders().get(i).getFood().get(j).getTypeOfDish().toString().equals(cook.getTypeOfCooker().toString())
							&& cook.getOrders().get(i).getFood().get(j).getDishStatus() == null){
						cook.getOrders().get(i).getFood().get(j).setDishStatus(DishStatus.received);
					}
				}
				break;
			}
		}

		for(int i = 0 ; i < cook.getOrders().size(); i++){
			if(cook.getOrders().get(i).getId() == orderId){
				for(int j = 0 ; j < cook.getOrders().get(i).getFood().size(); j++){
					if(cook.getOrders().get(i).getFood().get(j).getTypeOfDish().toString().equals(cook.getTypeOfCooker().toString())){
						cook.getOrders().get(i).getFood().get(j).setDishStatus(DishStatus.received);
					}
				}
				break;
				
			}
		}
		cookService.save(cook);
		//order.setFoodStatus(FoodStatus.received);
		return orderService.save(order);
	}

	
	@GetMapping(path = "/receivedFood")
	public ResponseEntity<List<Orderr>> receivedFood() {
		Long id = ((Cook) httpSession.getAttribute("user")).getId();
		Cook cook = cookService.findOne(id);
		List<Orderr> orders = cook.getOrders();

		Optional.ofNullable(orders).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		List<Orderr> order = new ArrayList<Orderr>();
		
		for(int i = 0 ; i < orders.size(); i++){
			if(orders.get(i).getFood().size() > 0 && orders.get(i).getFoodStatus().compareTo(FoodStatus.inPrepared) == 0){
				for(int j = 0; j < orders.get(i).getFood().size(); j++){
					if(orders.get(i).getFood().get(j).getDishStatus() != null && orders.get(i).getFood().get(j).getDishStatus().compareTo(DishStatus.received) == 0){
						order.add(orders.get(i));
						break;
					}
				}
			}
		}
		
		for(int i = 0 ; i < order.size(); i++){
			List<Dish> food = new ArrayList<Dish>();
			for(int j = 0 ; j < order.get(i).getFood().size(); j++){
				if(order.get(i).getFood().get(j).getTypeOfDish().toString().equals(cook.getTypeOfCooker().toString()) && 
						order.get(i).getFood().get(j).getDishStatus().compareTo(DishStatus.received) == 0){
					food.add(order.get(i).getFood().get(j));
				}
			}
			order.get(i).setFood(food);
		}
		
		return new ResponseEntity<>(order, HttpStatus.OK);
		
	}

	// 2.4 signazilira da je odgovarajuca narudzbina jela gotova
	@PutMapping(path = "/foodReady")
	@ResponseStatus(HttpStatus.OK)
	public Orderr foodReady(@RequestBody Orderr orderFinished) {
		Long id = ((Cook) httpSession.getAttribute("user")).getId();
		Cook cook = cookService.findOne(id);
		Optional.ofNullable(cook).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		Orderr orders = orderService.findOne(orderFinished.getId());
		
		for(int i = 0; i < orders.getFood().size(); i++){
			for(int j = 0 ; j < orderFinished.getFood().size(); j++){
				if(orders.getFood().get(i).getId() == orderFinished.getFood().get(j).getId()
						&& orders.getFood().get(i).getDishStatus().compareTo(DishStatus.received) == 0){
					orders.getFood().get(i).setDishStatus(DishStatus.finished);
					//break; //mislim da ovaj break ne treba proveriti tako sto cu staviti dva ista jela
				}
			}
		}

		for(int i = 0 ; i < cook.getOrders().size(); i++){
			if(cook.getOrders().get(i).getId() == orderFinished.getId() && cook.getOrders().get(i).getFood().size() > 0){
				for(int j = 0 ; j < cook.getOrders().get(i).getFood().size(); j++){
					if(cook.getOrders().get(i).getFood().get(j).getTypeOfDish().toString().equals(cook.getTypeOfCooker().toString())){
						cook.getOrders().get(i).getFood().get(j).setDishStatus(DishStatus.finished);
						cookService.save(cook);
						
					}
					
					//break;
				}
				break;
			}
		}
		//orders.setId(orderId);
		return orderService.save(orders);
	}
	
	@GetMapping(path = "/readyFood")
	public ResponseEntity<List<Orderr>> readyFood() {
		Long id = ((Cook) httpSession.getAttribute("user")).getId();
		Cook cook = cookService.findOne(id);
		List<Orderr> orders = cook.getOrders();

		Optional.ofNullable(orders).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		List<Orderr> order = new ArrayList<Orderr>();

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getFood().size() != 0 && orders.get(i).getFoodStatus() != null
					&& (orders.get(i).getFoodStatus().compareTo(FoodStatus.finished) == 0 || 
					orders.get(i).getFoodStatus().compareTo(FoodStatus.inPrepared) == 0)) {
				//for(int j = 0 ; j < orders.get(i).getFood().size(); j++){
					order.add(orders.get(i));
				//}
			}
		}
		return new ResponseEntity<>(order, HttpStatus.OK);
		
	}

}
*/