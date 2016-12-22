package app.employed.waiter;

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

import app.drink.DrinkService;
import app.order.OrderService;
import app.order.Orderr;

@RestController
@RequestMapping("/waiter")
public class WaiterController {

	private HttpSession httpSession;
	
	private final WaiterService waiterService;
	private final OrderService orderService;
	// private final DrinkService drinkService;
	// private List<Waiter> waiter;

	@Autowired
	public WaiterController(final HttpSession httpSession,final WaiterService service, final OrderService orderService,
			final DrinkService drinkService) {
		this.httpSession = httpSession;
		this.waiterService = service;
		this.orderService = orderService;
		// this.drinkService = drinkService;
		// this.waiter = service.findAll();
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
	public ResponseEntity<Waiter> findBartender(){
		Long id = ((Waiter) httpSession.getAttribute("user")).getId();
		Waiter waiter = waiterService.findOne(id);
		return new ResponseEntity<Waiter>(waiter, HttpStatus.OK);
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
		Waiter waiter = waiterService.findOne(id);
		Optional.ofNullable(waiter).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return waiter;
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		waiterService.delete(id);
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

	// 2.4. Konobar izmeni porudzbinu za odredjeni sto
	/*@PutMapping(path = "/{id}/order/{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public Orderr update(@PathVariable("id") Long id, @PathVariable("orderId") Long orderId, @Valid @RequestBody Orderr order) {
		Optional.ofNullable(service.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		Optional.ofNullable(orderService.findOne(orderId))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		order.setId(orderId);
		// orderService.findOne(id1).getTable();
		// service.findOne(id).getSegment().getTables().

		return orderService.save(order);
	}*/

	// 2.4 zavrsi porudzbinu i kreira racun
	/*@GetMapping(path = "/{id}/finishOrder/{id1}")
	//@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Orderr> finishTheOrder(@PathVariable Long id, @PathVariable Long id1, @Valid @RequestBody Orderr order) {
		order = orderService.findOne(id1);
		Optional.ofNullable(service.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		Optional.ofNullable(order)
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
	
		orderService.findOne(id1).setStatus(true);
		
		return new ResponseEntity<>(orderService.findOne(id1), HttpStatus.OK);
	
	}*/
}
