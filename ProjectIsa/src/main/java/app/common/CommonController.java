package app.common;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.bidder.BidderService;
import app.employed.bartender.BartenderService;
import app.guest.GuestService;
import app.manager.boss.BossManagerService;
import app.manager.restaurant.RestaurantManagerService;
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

	@Autowired
	public CommonController(final HttpSession httpSession, final BossManagerService bossManagerService,
			final RestaurantManagerService restaurantManagerService, final GuestService guestService,
			final SystemManagerService systemManagerService, final BidderService bidderService,
			final BartenderService bartenderService) {
		this.httpSession = httpSession;
		this.bossManagerService = bossManagerService;
		this.restaurantManagerService = restaurantManagerService;
		this.guestService = guestService;
		this.systemManagerService = systemManagerService;
		this.bidderService = bidderService;
		this.bartenderService = bartenderService;
	}

	@PostMapping(path = "/logIn")
	public ResponseEntity<String> logIn(@RequestBody User userInput) {
		User user = null;
		String userType = "";
		if (bossManagerService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = bossManagerService.findOne(userInput.getMail(), userInput.getPassword());
			userType = "boss";
		} else if (restaurantManagerService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = restaurantManagerService.findOne(userInput.getMail(), userInput.getPassword());
			userType = "restaurant";
		} else if (systemManagerService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = systemManagerService.findOne(userInput.getMail(), userInput.getPassword());
			userType = "system";
		} else if (guestService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = guestService.findOne(userInput.getMail(), userInput.getPassword());
			userType = "guest";
		} else if (bidderService.findOne(userInput.getMail(), userInput.getPassword()) != null) {
			user = bidderService.findOne(userInput.getMail(), userInput.getPassword());
			userType = "bidder";
		} else if (bartenderService.findOne(userInput.getMail(), userInput.getPassword()) != null){
			user = bartenderService.findOne(userInput.getMail(), userInput.getPassword());
			userType = "bartender";
		}
		if (user != null) {
			httpSession.setAttribute("user", user);
			return new ResponseEntity<>(userType, HttpStatus.OK);
		} else
			throw new NoSuchElementException("Ne postoji korisnik sa tim parametrima.");
	}

	@GetMapping(path = "/logOut")
	public void logOut() {
		httpSession.invalidate();
	}
	
	@GetMapping(path= "/getLoggedUser")
	public User getLoggedUser(){
		return (User) httpSession.getAttribute("user");
	}

}
