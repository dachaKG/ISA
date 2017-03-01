package app.employed.bartender;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.dish.Dish;
import app.dish.TypeOfDish;
import app.drink.Drink;
import app.employed.ClothesSize;
import app.employed.DefaultShift;
import app.employed.ShoesSize;
import app.employed.waiter.Waiter;
import app.order.DrinkStatus;
import app.order.FoodStatus;
import app.order.OrderService;
import app.order.Orderr;
import app.reservation.ReservationService;
import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BartenderControllerTest {

	
	//@InjectMocks
	private BartenderController bartenderController;

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private BartenderService bartenderService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private RestaurantService restaurantService;
	
	private MockMvc mockMvc;  
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	protected MockHttpSession session;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		Bartender b = getBartender();
		session = new MockHttpSession();
		session.setAttribute("user", b);
	}
	
	@Ignore
	@Test
	public void testBartenderController() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBartender() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/bartender").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.mail").value("a@gmail.com"));
		
	}

	@Test
	public void testSave() throws Exception {
		
		Bartender bartender = getBartenderFindOne();
		mockMvc.perform(put("/bartender/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bartender)))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.mail").value("danilo@gmail.com"));
		
	}
	
	@Test
	public void testFindOne(){
		/*this.mvc.perform(get("/bartender/1L")).andExpect(status().isOk())
			.andExpect(view().)
			.andExpect(content().string(Bartender.class.get));*/
	}


	@Test
	public void testEmployedBartenders() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/bartender/employedBartenders").session(session)).andDo(print())
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(2)));
	}

	@Test
	public void testUpdate() throws Exception {
		Bartender bartender = getBartenderFindOne();
		//String waiterPut = asJsonString(waiter);
		 mockMvc.perform(put("/bartender/profile")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(bartender)))
	                .andExpect(status().isOk());
	}

	@Test
	public void testChangePassword() throws Exception {
		Bartender bartender = getBartenderFindOne();
		bartender.setPassword("prodjiibreeeee");
		
		 mockMvc.perform(put("/bartender/changePassword/{id}", bartender.getId())
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(asJsonString(bartender)))
         .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
 		.andExpect(jsonPath("$.password").value("prodjiibreeeee"));
	}

	@Ignore
	@Test
	public void testFindAllOrdrers() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testDrinkReady() throws Exception {

	}
	
	
	///
	
	private Bartender getBartender(){
		Bartender bartender = new Bartender();
		bartender.setId(1L);
		bartender.setFirstname("Danilo");
		bartender.setLastname("Dimitrijevic");
		bartender.setMail("vlado94@gmail.com");
		bartender.setPassword("ssss");
		bartender.setRegistrated("1");
		bartender.setClothesSize(ClothesSize.XL);
		bartender.setShoesSize(ShoesSize.no46);
		bartender.setDefaultShift(DefaultShift.First);
		bartender.setOrders(new ArrayList<Orderr>());
		return bartender;
	}
	
	private Bartender getBartenderFindOne(){
		Bartender bartender = new Bartender();
		bartender.setId(2L);
		bartender.setFirstname("Vlado");
		bartender.setLastname("Stanojevic");
		bartender.setMail("danilo@gmail.com");
		bartender.setPassword("olja");
		bartender.setRegistrated("1");
		bartender.setClothesSize(ClothesSize.XL);
		bartender.setShoesSize(ShoesSize.no46);
		bartender.setDefaultShift(DefaultShift.First);
		
		return bartender;
	}
	
	private List<Bartender> getBartenders(){
		Bartender bartender1 = new Bartender();
		bartender1.setId(1L);
		bartender1.setFirstname("Vlado");
		bartender1.setLastname("Stanojevic");
		bartender1.setMail("vlado94@gmail.com");
		bartender1.setPassword("olja");
		bartender1.setRegistrated("1");
		bartender1.setClothesSize(ClothesSize.XL);
		bartender1.setShoesSize(ShoesSize.no46);
		bartender1.setDefaultShift(DefaultShift.First);
		
		Bartender bartender = new Bartender();
		bartender.setId(2L);
		bartender.setFirstname("Vlado");
		bartender.setLastname("Stanojevic");
		bartender.setMail("vlado94@gmail.com");
		bartender.setPassword("olja");
		bartender.setRegistrated("1");
		bartender.setClothesSize(ClothesSize.XL);
		bartender.setShoesSize(ShoesSize.no46);
		bartender.setDefaultShift(DefaultShift.First);
		
		Bartender bartender2 = new Bartender();
		bartender2.setId(3L);
		bartender2.setFirstname("Danilo");
		bartender2.setLastname("Stanojevic");
		bartender2.setMail("danillo@gmail.com");
		bartender2.setPassword("bbb");
		bartender2.setRegistrated("1");
		bartender2.setClothesSize(ClothesSize.XL);
		bartender2.setShoesSize(ShoesSize.no46);
		bartender2.setDefaultShift(DefaultShift.First);
		
		Bartender bartender3 = new Bartender();
		bartender3.setId(4L);
		bartender3.setFirstname("Basara");
		bartender3.setLastname("Stanojevic");
		bartender3.setMail("basara@gmail.com");
		bartender3.setPassword("aaaa");
		bartender3.setRegistrated("1");
		bartender3.setClothesSize(ClothesSize.XL);
		bartender3.setShoesSize(ShoesSize.no46);
		bartender3.setDefaultShift(DefaultShift.First);
		
		ArrayList<Bartender> list = new ArrayList<Bartender>();
		list.add(bartender1);
		list.add(bartender2);
		list.add(bartender3);
		list.add(bartender);
		
		return list;
	}
	
	public List<Orderr> getOrders(){
		List<Dish> food = getFood();
		List<Drink> drinks = getDrinks();
		List<Orderr> orders = new ArrayList<Orderr>();
		Orderr order = new Orderr();
		order.setId(1L);
		order.setTotal(0);
		order.setFood(food);
		order.setDrinks(drinks);
		order.setFoodStatus(FoodStatus.inPrepared);
		order.setDrinkStatus(DrinkStatus.inPrepared);
		
		Orderr order1 = new Orderr();
		order1.setId(2L);
		order1.setTotal(0);
		order1.setFood(food);
		order1.setDrinks(drinks);
		order1.setFoodStatus(FoodStatus.inPrepared);
		order1.setDrinkStatus(DrinkStatus.inPrepared);
		
		orders.add(order);
		orders.add(order1);
		
		return orders;
		
	}
	
	public List<Dish> getFood(){
		List<Dish> food = new ArrayList<Dish>();
		Dish dish = new Dish();
		dish.setId(1L);
		dish.setName("pica");
		dish.setText("aaaaa");
		dish.setPrice(130L);
		dish.setCount(20);
		dish.setTypeOfDish(TypeOfDish.baked);
		dish.setRate(4.2);
		
		Dish dish1 = new Dish();
		dish1.setId(2L);
		dish1.setName("sarma");
		dish1.setText("ssss");
		dish1.setPrice(300L);
		dish1.setCount(20);
		dish1.setTypeOfDish(TypeOfDish.cooked);
		dish1.setRate(5.0);
		
		Dish dish2 = new Dish();
		dish2.setId(3L);
		dish2.setName("pasulj");
		dish2.setText("aaaaa");
		dish2.setPrice(250L);
		dish2.setCount(20);
		dish2.setTypeOfDish(TypeOfDish.cooked);
		dish2.setRate(4.8);
		
		food.add(dish);
		food.add(dish1);
		food.add(dish2);
		
		return food;

	}
	
	public List<Drink> getDrinks(){
		List<Drink> drinks = new ArrayList<Drink>();
		
		Drink drink = new Drink();
		drink.setId(1L);
		drink.setName("pivo");
		drink.setText("tamno niksicko");
		drink.setPrice(200L);
		drink.setCount(50);
		
		Drink drink1 = new Drink();
		drink1.setId(2L);
		drink1.setName("sok");
		drink1.setText("vocni");
		drink1.setPrice(220L);
		drink1.setCount(50);
		
		drinks.add(drink);
		drinks.add(drink1);
		
		return drinks;
	}
	
	private List<Restaurant> getRestaurants(){
		Restaurant restaurant = new Restaurant();
		restaurant.setId(1L);
		restaurant.setName("r1");
		restaurant.setSummRate(4.0);
		restaurant.setCountry("srbija");
		restaurant.setDescription("blaaa");
		restaurant.setCity("Kragujevac");
		restaurant.setStreet("kkkkk");
		restaurant.setNumber("111");
		restaurant.setBartenders(getBartenders());
		
		List<Bartender> bartenders = new ArrayList<Bartender>();
		Bartender bartender2 = new Bartender();
		bartender2.setId(5L);
		bartender2.setFirstname("rrr");
		bartender2.setLastname("ww");
		bartender2.setMail("danillo@gmail.com");
		bartender2.setPassword("bbb");
		bartender2.setRegistrated("1");
		bartender2.setClothesSize(ClothesSize.XL);
		bartender2.setShoesSize(ShoesSize.no46);
		bartender2.setDefaultShift(DefaultShift.First);
		
		Bartender bartender3 = new Bartender();
		bartender3.setId(6L);
		bartender3.setFirstname("ss");
		bartender3.setLastname("s");
		bartender3.setMail("basara@gmail.com");
		bartender3.setPassword("aaaa");
		bartender3.setRegistrated("1");
		bartender3.setClothesSize(ClothesSize.XL);
		bartender3.setShoesSize(ShoesSize.no46);
		bartender3.setDefaultShift(DefaultShift.First);
		
		bartenders.add(bartender2);
		bartenders.add(bartender3);
		
		Restaurant restaurant1 = new Restaurant();
		restaurant1.setId(2L);
		restaurant1.setName("r111");
		restaurant1.setSummRate(4.0);
		restaurant1.setCountry("srbijaaaa");
		restaurant1.setDescription("sadd");
		restaurant1.setCity("Kragujevac");
		restaurant1.setStreet("dddd");
		restaurant1.setNumber("12");
		restaurant1.setBartenders(bartenders);
		
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		restaurants.add(restaurant);
		restaurants.add(restaurant);
		return restaurants;
		
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
}
