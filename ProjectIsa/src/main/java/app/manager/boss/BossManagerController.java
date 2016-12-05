package app.manager.boss;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.manager.system.SystemManager;
import app.manager.system.SystemManagerService;

@RestController
@RequestMapping("/boss")
public class BossManagerController {

	private final BossManagerService serviceBoss;
	private final SystemManagerService serviceSystemManager;

	@Autowired
	public BossManagerController(final BossManagerService serviceBoss,
			final SystemManagerService serviceSystemManager) {
		this.serviceBoss = serviceBoss;
		this.serviceSystemManager = serviceSystemManager;
	}

	// dodavanje novog boss menadzera, ako ima potrebe
	@PostMapping(path = "/boss")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveBoss(@Valid @RequestBody BossManager bossManager) {
		bossManager.setId(null);
		serviceBoss.save(bossManager);
	}

	// izlistavanja svih menadzera sistema
	@GetMapping
	public ResponseEntity<List<SystemManager>> findAll() {
		return new ResponseEntity<>(serviceSystemManager.findAll(), HttpStatus.OK);
	}

	// 2.9
	// dodavanje novog menadzera sistema
	@PostMapping(path = "/manager")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveSystemManager(@Valid @RequestBody SystemManager systemManager) {
		systemManager.setId(null);
		serviceSystemManager.save(systemManager);
	}

	// omogucena izmena nekog menadzera sistema, koju vrsi glavni menadzer
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SystemManager updateSystemManager(@PathVariable Long id, @Valid @RequestBody SystemManager systemManager) {
		Optional.ofNullable(serviceSystemManager.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		systemManager.setId(id);
		return serviceSystemManager.save(systemManager);
	}

	// nalazenje jednog menadzera sistema
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SystemManager findOne(@PathVariable Long id) {
		SystemManager systemManager = serviceSystemManager.findOne(id);
		Optional.ofNullable(systemManager).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return systemManager;
	}

	// brisanje menadzera sistema
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		serviceSystemManager.delete(id);
	}
}