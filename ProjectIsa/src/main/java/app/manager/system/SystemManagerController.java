package app.manager.system;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;

@RestController
@RequestMapping("/systemManager")
public class SystemManagerController {
	private final RestaurantService service;

	@Autowired
	public SystemManagerController(final RestaurantService service) {
		this.service = service;
	}

	// 2.9
	// registrovanje novih restorana, sa vec postojecim menadzerom
	// restorana,mora postojati menadzer da bi
	// bio postavljen za menadzera datog restorana
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Restaurant restaurant) {
		restaurant.setId(null);
		service.save(restaurant);
	}

	// pronalazak bilo kog restorana
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Restaurant findOne(@PathVariable Long id) {
		Restaurant restaurant = service.findOne(id);
		Optional.ofNullable(restaurant).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return restaurant;
	}

	// brisanje restorana
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	// izmena restorana
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Restaurant update(@PathVariable Long id, @Valid @RequestBody Restaurant restaurant) {
		Optional.ofNullable(service.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		restaurant.setId(id);
		return service.save(restaurant);
	}
}