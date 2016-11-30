package app.users;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

public class UserController {
	private final UserService service;

	@Autowired
	public UserController(final UserService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody User user) {
		user.setId(null);
		service.save(user);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User findOne(@PathVariable Long id) {
		User disk = service.findOne(id);
		Optional.ofNullable(disk).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return disk;
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User update(@PathVariable Long id, @Valid @RequestBody User newHardDisk) {
		Optional.ofNullable(service.findOne(id)).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		newHardDisk.setId(id);
		return service.save(newHardDisk);
	}
}
