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

import app.manager.boss.BossManager;

@RestController
@RequestMapping("/guest")
public class GuestController {
	
	private final GuestService service;
	private HttpSession httpSession;

	@Autowired
	public GuestController(final HttpSession httpSession, final GuestService service) {
		this.service = service;
		this.httpSession = httpSession;
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

	//2.2
	//izmena informacija o gostu
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Guest update(@PathVariable Long id, @Valid @RequestBody Guest guest) {
		Optional.ofNullable(service.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		guest.setId(id);
		return service.save(guest);
	}
	
	
	//kada se klikne na link iz maila
	@PutMapping(path = "/activate/{acNum}")
	@ResponseStatus(HttpStatus.OK)
	public void activateGuest(@PathVariable String acNum){
		service.activate(acNum);
	}
	
	
	
	@GetMapping(path = "/findByFirstAndLastName/{inputStr}")
	public List<Guest> findByFirstAndLastName(@PathVariable String inputStr){
		return service.findByFirstAndLastName(inputStr);
	}
	
	
}