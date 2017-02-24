package app.employed.bartender;

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

import app.drink.Drink;
import app.employed.cook.Cook;
import app.order.DrinkStatus;
import app.order.OrderService;
import app.order.Orderr;

@RestController
@RequestMapping("/bartender")
public class BartenderController {

	private HttpSession httpSession;

	private final BartenderService bartenderService;
	private final OrderService orderService;

	@Autowired
	public BartenderController(final HttpSession httpSession, final BartenderService bartenderService,
			final OrderService orderService) {
		this.httpSession = httpSession;
		this.bartenderService = bartenderService;
		this.orderService = orderService;
	}

	@SuppressWarnings("unused")
	@GetMapping("/checkRights")
	public boolean checkRights() {
		try {
			Bartender bartender = ((Bartender) httpSession.getAttribute("user"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@GetMapping
	public ResponseEntity<Bartender> findBartender() {
		Long id = ((Bartender) httpSession.getAttribute("user")).getId();
		Bartender bartender = bartenderService.findOne(id);
		return new ResponseEntity<Bartender>(bartender, HttpStatus.OK);
	}

	/*
	 * @GetMapping public ResponseEntity<List<Bartender>> findAll() { return new
	 * ResponseEntity<>(bartenderService.findAll(), HttpStatus.OK); }
	 */

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Bartender bartender) {
		bartender.setId(null);
		bartender.setRegistrated("0");
		bartenderService.save(bartender);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Bartender findOne(@PathVariable Long id) {
		Bartender bartender = bartenderService.findOne(id);
		Optional.ofNullable(bartender).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return bartender;
	}

	// 2.4. sanker ima mogucnost da azurira podatke
	@PutMapping(path = "/profile")
	@ResponseStatus(HttpStatus.OK)
	public Bartender update(@Valid @RequestBody Bartender bartender) {
		Optional.ofNullable(bartenderService.findOne(bartender.getId()))
					.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		//Bartender bar = bartenderService.findOne(bartender.getId());
		//bartender.setId(id);
		
		return bartenderService.save(bartender);
	}

	// 2.4 prikaz porudzbina za sankera
	@GetMapping(path = "/orders")
	public ResponseEntity<List<Orderr>> findAllOrdrers() {
		Long id = ((Bartender) httpSession.getAttribute("user")).getId();
	    Bartender bartender = bartenderService.findOne(id);
		//List<Orderr> orders = bartenderService.findOne(id).getOrders();
		
		List<Orderr> allOrders = orderService.findAll();
		//Optional.ofNullable(orders).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		
		List<Orderr> orders = new ArrayList<Orderr>();

		for (int i = 0; i < allOrders.size(); i++) {
			if (allOrders.get(i).getDrinks().size() > 0 && allOrders.get(i).getDrinkStatus() != null
					&& allOrders.get(i).getDrinkStatus().compareTo(DrinkStatus.inPrepared) == 0) {
				
				orders.add(allOrders.get(i));
				
			}
		}
		bartender.getOrders().addAll(orders);
		bartenderService.save(bartender);
		

		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// spisak pica koja su spremna
	@GetMapping(path = "/readyDrinks")
	public ResponseEntity<List<Orderr>> readyDrinks() {
		Long id = ((Bartender) httpSession.getAttribute("user")).getId();
		// Bartender bartender = ((Bartender) httpSession.getAttribute("user"));
		Bartender bartender = bartenderService.findOne(id);
		List<Orderr> orders = bartender.getOrders();

		Optional.ofNullable(orders).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		List<Orderr> order = new ArrayList<Orderr>();

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getDrinks().size() != 0 && orders.get(i).getDrinkStatus() != null
					&& orders.get(i).getDrinkStatus().compareTo(DrinkStatus.finished) == 0) {
				
					order.add(orders.get(i));
				
			}
		}

		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	// 2.4 sanker signalizir da je odgovarajuce pice spremno
	@GetMapping(path = "/drinkReady/{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public Orderr drinkReady(@PathVariable Long orderId) {
		Optional.ofNullable(orderService.findOne(orderId))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		Long id = ((Bartender) httpSession.getAttribute("user")).getId();
		Bartender bartender = bartenderService.findOne(id);
		Orderr order = orderService.findOne(orderId);
		order.setDrinkStatus(DrinkStatus.finished);
		for(int i = 0 ; i < bartender.getOrders().size(); i++){
			if(bartender.getOrders().get(i).getId() == orderId){
				bartender.getOrders().get(i).setDrinkStatus(DrinkStatus.finished);
				
				break;
			}
		}
		bartenderService.save(bartender);
		order.setId(orderId);
		return orderService.save(order);
	}

}
