package app.employed.bartender;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.drink.Drink;
import app.order.Orderr;

@RestController
@RequestMapping("/bartender")
public class BartenderController {

	private final BartenderService bartenderService;

	@Autowired
	public BartenderController(final BartenderService bartenderService) {
		this.bartenderService = bartenderService;
	}

	@GetMapping
	public ResponseEntity<List<Bartender>> findAll() {
		return new ResponseEntity<>(bartenderService.findAll(), HttpStatus.OK);
	}

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
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Bartender update(@PathVariable Long id, @Valid @RequestBody Bartender bartender) {
		Optional.ofNullable(bartenderService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		bartender.setId(id);
		return bartenderService.save(bartender);
	}

	@GetMapping(path = "/{id}/order")
	public ResponseEntity<List<Drink>> findAllOrdres(@PathVariable Long id) {

		List<Orderr> orders = bartenderService.findOne(id).getOrders();
		Optional.ofNullable(orders).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		List<Drink> drinks = new ArrayList<Drink>();

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getDrinks().size() != 0) {
				for (int j = 0; j < orders.get(i).getDrinks().size(); j++) {
					drinks.add(orders.get(i).getDrinks().get(j));
				}
			}
		}

		return new ResponseEntity<>(drinks, HttpStatus.OK);
	}

}
