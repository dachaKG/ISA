package app.manager.restaurant;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
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
import app.dish.Dish;
import app.drink.Drink;
import app.employed.bartender.Bartender;
import app.employed.cook.Cook;
import app.employed.waiter.Waiter;
import app.offer.Offer;
import app.offer.OfferService;
import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;
import app.restaurant.Segment;
import app.restaurant.SegmentService;
import app.restaurant.TableService;
import app.restaurant.restaurantOrder.RestaurantOrderService;
import app.restaurant.restaurantOrder.RestaurantOrderr;
@RestController
@RequestMapping("/restaurantManager")
public class RestaurantManagerController {

	private HttpSession httpSession;
	private RestaurantService restaurantService;
	private BidderService bidderService;
	private RestaurantOrderService restaurantOrderService;
	private RestaurantManagerService restaurantManagerService;
	private OfferService offerService;
	private SegmentService segmentService;
	private TableService tableService;

	@Autowired
	public RestaurantManagerController(final HttpSession httpSession, final RestaurantService restaurantService,
			final RestaurantManagerService restaurantManagerService,final BidderService bidderService,
			final RestaurantOrderService restaurantOrderService,final OfferService offerService,
			final SegmentService segmentService, final TableService tableService) {
		this.httpSession = httpSession;
		this.restaurantService = restaurantService;
		this.bidderService = bidderService;
		this.restaurantOrderService = restaurantOrderService;
		this.restaurantManagerService = restaurantManagerService;
		this.offerService = offerService;
		this.segmentService = segmentService;
		this.tableService = tableService;
		
	}

	@GetMapping("/checkRights")
	@ResponseStatus(HttpStatus.OK)
	public RestaurantManager checkRights() {
		try {
			return ((RestaurantManager) httpSession.getAttribute("user"));
		} catch (Exception e) {
			return null;
		}
	}

	// spusta se pretraga na repo,menja se kasnije kad se uvede da restoran
	// sadrzi vise menadzera
	@GetMapping("/restaurant")
	@ResponseStatus(HttpStatus.OK)
	public Restaurant findRestaurantForRestaurantManager() {
		Long userId = ((RestaurantManager) httpSession.getAttribute("user")).getId();
		List<Restaurant> restaurants = restaurantService.findAll();
		for (int i = 0; i < restaurants.size(); i++) {
			Restaurant restaurant = restaurants.get(i);
			for (int j = 0; j < restaurant.getRestaurantManagers().size(); j++) 
				if (restaurant.getRestaurantManagers().get(j).getId() == userId) {
					//sluzi za inicijalizaciju posto preko data u konsturktoru nzm kako da dam default values
					if(restaurant.getSummRate() == null) {
						restaurant.setNumRate(0);
						restaurant.setSummRate(0);
						restaurantService.save(restaurant);
					}
					return restaurant;
				}
		}
		return null;
	}

	@PostMapping(path = "/restaurant/saveDrink")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveDrink(@Valid @RequestBody Drink drink) {
		Restaurant restaurant = findRestaurantForRestaurantManager();
		restaurant.getDrinks().add(drink);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveDish")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveDish(@Valid @RequestBody Dish dish) {
		Restaurant restaurant = findRestaurantForRestaurantManager();
		restaurant.getFood().add(dish);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveWaiter")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveWaiter(@Valid @RequestBody Waiter waiter) {
		waiter.setRegistrated("0");
		Restaurant restaurant = findRestaurantForRestaurantManager();
		restaurant.getWaiters().add(waiter);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveCook")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveCook(@Valid @RequestBody Cook cook) {
		cook.setRegistrated("0");
		Restaurant restaurant = findRestaurantForRestaurantManager();
		restaurant.getCooks().add(cook);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveBartender")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveCook(@Valid @RequestBody Bartender bartender) {
		bartender.setRegistrated("0");
		Restaurant restaurant = findRestaurantForRestaurantManager();
		restaurant.getBartenders().add(bartender);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveBidder")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveBidder(@Valid @RequestBody Bidder bidder) {
		bidder.setRegistrated("0");
		Restaurant restaurant = findRestaurantForRestaurantManager();
		restaurant.getBidders().add(bidder);
		restaurantService.save(restaurant);
 	}
	
	@GetMapping("/showFreeBidders")
	public ArrayList<Bidder> showFreeBidders() {
		ArrayList<Bidder> bidders = new ArrayList<Bidder>();
		List<Bidder> allBidders = bidderService.findAll();
		Restaurant restaurant = findRestaurantForRestaurantManager();
		for(int i=0;i<allBidders.size();i++) {
			if(!contains(restaurant, allBidders.get(i)))
				bidders.add(allBidders.get(i));
		}
		return bidders;
	}
	
	private boolean contains(Restaurant restaurant,Bidder bidder) {
		for(int j=0;j<restaurant.getBidders().size();j++) {
			if(bidder.getId() == restaurant.getBidders().get(j).getId())
				return true;
		}
		return false;
	}
	
	@PostMapping(path = "/restaurant/connectBidder")
	@ResponseStatus(HttpStatus.CREATED)
	public void connectBidder(@Valid @RequestBody Bidder bidder) {
		Bidder bidderOld = bidderService.findOne(bidder.getId());
		Restaurant restaurant = findRestaurantForRestaurantManager();
		restaurant.getBidders().add(bidderOld);
		restaurantService.save(restaurant);
	}

 
	@PostMapping(path = "/restaurant/createNewOffer")
	@ResponseStatus(HttpStatus.CREATED)
	public void createNewOffer(@Valid @RequestBody RestaurantOrderr restaurantOrderr) {
		Dish dish = restaurantOrderr.getDish();
		Drink drink = restaurantOrderr.getDrink();
		Restaurant restaurant = findRestaurantForRestaurantManager();
		setObject(dish,drink,restaurant,restaurantOrderr);
		restaurant.getRestaurantOrders().add(restaurantOrderr);
		restaurantOrderService.save(restaurantOrderr);
		restaurantService.save(restaurant);
 	}
	
	@PostMapping(path = "/restaurant/acceptRestaurantOrder")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void acceptRestaurantOrder(@Valid @RequestBody RestaurantOrderr restaurantOrderr) {
		if(restaurantOrderr.getOrderActive().equals("open")) {
			restaurantOrderr.setOrderActive("closed");
			for(int i=0;i<restaurantOrderr.getOffers().size();i++) {
				if(restaurantOrderr.getOffers().get(i).getBidder().getId() == restaurantOrderr.getIdFromChoosenBidder()) {
					restaurantOrderr.getOffers().get(i).setAccepted("accepted");
				}
				else
					restaurantOrderr.getOffers().get(i).setAccepted("rejected");
				offerService.save(restaurantOrderr.getOffers().get(i));
			}
			Restaurant restaurant = findRestaurantForRestaurantManager();
			restaurantOrderService.save(restaurantOrderr);
			restaurantService.save(restaurant);
		}
		else {
			try {
				throw new Exception("Not legal offer.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
 	}
	
	private void setObject(Dish dish,Drink drink,Restaurant restaurant, RestaurantOrderr restaurantOrderr) {
		restaurantOrderr.setOrderActive("0");
		if(restaurantOrderr.getStartDate().before(restaurantOrderr.getEndDate())) {
			if(dish != null) {
				for(int i=0;i<restaurant.getFood().size();i++)
					if(restaurant.getFood().get(i).getId() == dish.getId()) {
						restaurantOrderr.setDish(restaurant.getFood().get(i));
						break;
					}
			}
			else if(drink != null) {
				for(int i=0;i<restaurant.getDrinks().size();i++)
					if(restaurant.getDrinks().get(i).getId() == drink.getId()) {
						restaurantOrderr.setDrink(restaurant.getDrinks().get(i));
						break;
					}
			}
			restaurantOrderr.setOffers(new ArrayList<Offer>());
			restaurantOrderr.setOrderActive("open");
		}
		else
			throw new DateTimeException("Wrong date.");
	}
	
	@PutMapping(path = "/{id}")
	public RestaurantManager updateRestaurantManager(@PathVariable Long id,
			@Valid @RequestBody RestaurantManager restaurantManager) {
		Optional.ofNullable(restaurantManagerService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		restaurantManager.setId(id);
		return restaurantManagerService.save(restaurantManager);
	}
	
	@PutMapping(path = "/restaurant/makeConfig/{xaxis}/{yaxis}")
	public void makeConfig(@PathVariable("xaxis") Long xaxis, @PathVariable("yaxis") Long yaxis){
		
		Restaurant restaurant = findRestaurantForRestaurantManager();
		//RESITI OVO KAKO TREBA
		restaurant.getSegments().get(0).getTables().clear();
		
		for(int x = 0; x<xaxis; x++){
			for(int y  =0; y<yaxis; y++){
				app.restaurant.Table t = new app.restaurant.Table("Table", x, y, app.restaurant.Table.NOT_EXISTS);
				segmentService.findAll().get(0).getTables().add(t);
				tableService.save(t);
			}
		}
	}
	
	
	@GetMapping(path="/restaurant/getTables")
	public List<app.restaurant.Table> getTables(){
		
		Restaurant restaurant = findRestaurantForRestaurantManager();
		// prvi put napravi default segment
		if(restaurant.getSegments().size()<1){
			Segment seg = new Segment("default");
			restaurant.getSegments().add(seg);
			segmentService.save(seg);
		}
		ArrayList<app.restaurant.Table> outTables = new ArrayList<app.restaurant.Table>();
		for(int i=0; i<restaurant.getSegments().size(); i++){
			outTables.addAll(restaurant.getSegments().get(i).getTables());
		}
		return outTables;
	}
	
	@PostMapping(path ="/restaurant/addSegment")
	public void addSegment(@RequestBody Segment segment){
		Restaurant restaurant = findRestaurantForRestaurantManager();
		restaurant.getSegments().add(segment);
		segmentService.save(segment);
	}
	
	
	@GetMapping(path = "/restaurant/getSegments")
	public List<Segment> getSegments(){
		Restaurant rest = findRestaurantForRestaurantManager();
		return rest.getSegments();
	}
	
	@PutMapping(path = "/restaurant/table/{id}")
	public app.restaurant.Table updateTable(@PathVariable Long id,
			@RequestBody app.restaurant.Table table) {
		System.out.println("idemo cuvati tabelu: "+table.getName() + " "+table.getSegmentName() + " "+table.getStatus());
		app.restaurant.Table pomtab = tableService.findOne(id);
		table.setXPos(pomtab.getXPos());
		table.setYPos(pomtab.getYPos());
		table.setId(id);
		
		return tableService.save(table);
	}
	
	
	
}