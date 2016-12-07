package app.order;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrderController {

	private final OrderService orderService;

	@Autowired
	public OrderController(final OrderService service) {
		this.orderService = service;
	}

	// listanje svih porudzbina
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Order order) {
		order.setId(null);
		orderService.save(order);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Order findOne(@PathVariable Long id) {
		Order order = orderService.findOne(id);
		Optional.ofNullable(order).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return order;
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		orderService.delete(id);
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Order update(@PathVariable Long id, @Valid @RequestBody Order order) {
		Optional.ofNullable(orderService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		order.setId(id);
		return orderService.save(order);
	}

}
