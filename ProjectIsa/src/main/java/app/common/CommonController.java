package app.common;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import app.bidder.Bidder;
import app.bidder.BidderService;
import app.employed.bartender.Bartender;
import app.employed.bartender.BartenderService;
import app.employed.cook.Cook;
import app.employed.cook.CookService;
import app.employed.waiter.Waiter;
import app.employed.waiter.WaiterService;
import app.guest.Guest;
import app.guest.GuestService;
import app.manager.boss.BossManager;
import app.manager.boss.BossManagerService;
import app.manager.restaurant.RestaurantManager;
import app.manager.restaurant.RestaurantManagerService;
import app.manager.system.SystemManager;
import app.manager.system.SystemManagerService;

@RestController
@RequestMapping("/commonController")
public class CommonController {

	private HttpSession httpSession;

	private BossManagerService bossManagerService;
	private RestaurantManagerService restaurantManagerService;
	private GuestService guestService;
	private SystemManagerService systemManagerService;
	private BidderService bidderService;
	private BartenderService bartenderService;
	private CookService cookService;
	private WaiterService waiterService;

	@Autowired
	public CommonController(final HttpSession httpSession, final BossManagerService bossManagerService,
			final RestaurantManagerService restaurantManagerService, final GuestService guestService,
			final SystemManagerService systemManagerService, final BidderService bidderService,
			final CookService cookService, final WaiterService waiterService, final BartenderService bartenderService) {
		this.httpSession = httpSession;
		this.bossManagerService = bossManagerService;
		this.restaurantManagerService = restaurantManagerService;
		this.guestService = guestService;
		this.systemManagerService = systemManagerService;
		this.bidderService = bidderService;
		this.bartenderService = bartenderService;
		this.cookService = cookService;
		this.waiterService = waiterService;
	}

	@PostMapping(path = "/logIn")
	public ResponseEntity<String> logIn(@RequestBody User userInput) {
		User user = null;
		String userType = "";
		Long id = 0l;
		if (bossManagerService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = bossManagerService.findOne(userInput.getMail(), userInput.getPassword());
			id = bossManagerService.findOne(userInput.getMail(), userInput.getPassword()).getId();
			userType = "boss";
		} else if (restaurantManagerService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = restaurantManagerService.findOne(userInput.getMail(), userInput.getPassword());
			id = restaurantManagerService.findOne(userInput.getMail(), userInput.getPassword()).getId();
			userType = "restaurant";
		} else if (systemManagerService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = systemManagerService.findOne(userInput.getMail(), userInput.getPassword());
			id = systemManagerService.findOne(userInput.getMail(), userInput.getPassword()).getId();
			userType = "system";
		} else if (guestService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = guestService.findOne(userInput.getMail(), userInput.getPassword());
			id = guestService.findOne(userInput.getMail(), userInput.getPassword()).getId();
			userType = "guest";
		} else if (bidderService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = bidderService.findOne(userInput.getMail(), userInput.getPassword());
			id = bidderService.findOne(userInput.getMail(), userInput.getPassword()).getId();
			userType = "bidder";
		} else if (bartenderService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = bartenderService.findOne(userInput.getMail(), userInput.getPassword());
			id = bartenderService.findOne(userInput.getMail(), userInput.getPassword()).getId();
			userType = "bartender";
		} else if (waiterService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = waiterService.findOne(userInput.getMail(), userInput.getPassword());
			id = waiterService.findOne(userInput.getMail(), userInput.getPassword()).getId();
			userType = "waiter";
		} else if (cookService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = cookService.findOne(userInput.getMail(), userInput.getPassword());
			id = cookService.findOne(userInput.getMail(), userInput.getPassword()).getId();
			userType = "cook";
		}
		if (user != null) {
			httpSession.setAttribute("user", user);
			if (!user.getRegistrated().equals("0") || userType.equals("guest"))
				return new ResponseEntity<>(userType, HttpStatus.OK);
			return new ResponseEntity<>("" + id, HttpStatus.OK);
		} else
			throw new NoSuchElementException("Ne postoji korisnik sa tim parametrima.");
	}

	@GetMapping(path = "/logOut")
	public void logOut() {
		httpSession.invalidate();
	}

	@GetMapping(path = "/getLoggedUser")
	public User getLoggedUser() {
		return (User) httpSession.getAttribute("user");
	}

	// registracija gosta
	@PostMapping(path = "/registration")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Guest guest) {
		guest.setId(null);
		guest.setRegistrated("0");
		guestService.save(guest);
	}

	// izmena sifre nakon prvog logovanja
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long id, @Valid @RequestBody User userInput) {
		if (bossManagerService.findOneWithMail(userInput.getMail()) != null) {
			BossManager bossManager = bossManagerService.findOne(id);
			bossManager.setId(id);
			bossManager.setRegistrated("1");
			bossManager.setPassword(userInput.getPassword());
			bossManagerService.save(bossManager);
		} else if (restaurantManagerService.findOneWithMail(userInput.getMail()) != null) {
			RestaurantManager restaurantManager = restaurantManagerService.findOne(id);
			restaurantManager.setId(id);
			restaurantManager.setRegistrated("1");
			restaurantManager.setPassword(userInput.getPassword());
			restaurantManagerService.save(restaurantManager);
		} else if (systemManagerService.findOneWithMail(userInput.getMail()) != null) {
			SystemManager systemManager = systemManagerService.findOne(id);
			systemManager.setId(id);
			systemManager.setRegistrated("1");
			systemManager.setPassword(userInput.getPassword());
			systemManagerService.save(systemManager);
		} 
		/*else if (guestService.findOneWithMail(userInput.getMail()) != null) {
			Guest guest = guestService.findOne(id);
			guest.setId(id);
			guest.setRegistrated("1");
			guest.setPassword(userInput.getPassword());
			guestService.save(guest);
		}*/
		
		  else if (bidderService.findOneWithMail(userInput.getMail()) != null) {
			Bidder bidder = bidderService.findOne(id);
			bidder.setId(id);
			bidder.setRegistrated("1");
			bidder.setPassword(userInput.getPassword());
			bidderService.save(bidder);
		} else if (bartenderService.findOneWithMail(userInput.getMail()) != null) {
			Bartender bartender = bartenderService.findOne(id);
			bartender.setId(id);
			bartender.setRegistrated("1");
			bartender.setPassword(userInput.getPassword());
			bartenderService.save(bartender);
		} else if (cookService.findOneWithMail(userInput.getMail()) != null) {
			Cook cook = cookService.findOne(id);
			cook.setId(id);
			cook.setRegistrated("1");
			cook.setPassword(userInput.getPassword());
			cookService.save(cook);
		} else if (waiterService.findOneWithMail(userInput.getMail()) != null) {
			Waiter waiter = waiterService.findOne(id);
			waiter.setId(id);
			waiter.setRegistrated("1");
			waiter.setPassword(userInput.getPassword());
			waiterService.save(waiter);
		}
	}
}