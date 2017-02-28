package app.employed.bartender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import app.common.AbstractRestControllerTest;
import app.dish.Dish;
import app.dish.TypeOfDish;
import app.drink.Drink;
import app.employed.ClothesSize;
import app.employed.DefaultShift;
import app.employed.ShoesSize;
import app.order.DrinkStatus;
import app.order.FoodStatus;
import app.order.OrderService;
import app.order.Orderr;
import app.reservation.ReservationService;
import app.restaurant.RestaurantService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BartenderControllerTest extends AbstractRestControllerTest {

	
	@InjectMocks
	private BartenderController bartenderController;

	@Mock
	private HttpSession httpSession;
	
	@Mock
	private BartenderService bartenderService;

	@Mock
	private OrderService orderService;

	@Mock
	private ReservationService reservationService;

	@Mock
	private RestaurantService restaurantService;
	
	PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver();

	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		bartenderController = new BartenderController(httpSession, bartenderService, orderService,
				reservationService, restaurantService);
		mockMvc = MockMvcBuilders.standaloneSetup(bartenderController).setCustomArgumentResolvers(pageResolver)
				.build();
	}

	@Test
	public void testBartenderController() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testCheckRights() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBartender() throws Exception {
		when(httpSession.getAttribute("user")).thenReturn(getBartender(2L));
		
		when(bartenderService.findOne(2L)).thenReturn(getBartender(2L));
		//Bartender bartender = bartenderService.findOne(2L);
		mockMvc.perform(get("/bartender")).andDo(MockMvcResultHandlers.print());
		
		//verify(bartenderService).findOne(2L);
		assertThat(2L);
		
	}

	@Test
	public void testSave() throws IOException, Exception {
		Bartender bartender = getBartender();
		bartender.setId(null);
		bartender.setRegistrated("0");
		when(bartenderService.save(bartender)).thenReturn(getBartender());
	
		mockMvc.perform(post("/bartender").content(convertObjectToJsonBytes(bartender))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isCreated());
		bartender = bartenderService.save(bartender);
		//bartenderService.save(bartender);
		//verify(bartenderService).save(bartender);
		assertThat(bartender);
	}

	@Test
	public void testFindOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmployedBartenders() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() throws IOException, Exception {
		when(bartenderService.findOne(1l)).thenReturn(getBartender());
		when(bartenderService.save(getBartender())).thenReturn(getBartender());
		mockMvc.perform(put("/bartender", getBartender()).content(convertObjectToJsonBytes(getBartender()))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(MockMvcResultHandlers.print());// .andExpect(status().isOk());

		verify(bartenderService).save(getBartender());
	}

	@Test
	public void testChangePassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllOrdrers() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrinkReady() throws Exception {
		
		when(httpSession.getAttribute("user")).thenReturn(getBartender(2L));
		
		when(bartenderService.findOne(2L)).thenReturn(getBartender(2L));
		
		
		
		
		
		mockMvc.perform(get("/bartender/drinkReady/{orderId}/{versionId}",2L,0)).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void testGetActiveReservations() {
		fail("Not yet implemented");
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
		
		return bartender;
	}
	
	private Bartender getBartender(Long id){
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
		
		return bartender;
	}
	
	private Page<Bartender> getBartenders(){
		Bartender bartender = new Bartender();
		bartender.setId(1L);
		bartender.setFirstname("Vlado");
		bartender.setLastname("Stanojevic");
		bartender.setMail("vlado94@gmail.com");
		bartender.setPassword("olja");
		bartender.setRegistrated("1");
		bartender.setClothesSize(ClothesSize.XL);
		bartender.setShoesSize(ShoesSize.no46);
		bartender.setDefaultShift(DefaultShift.First);
		
		Bartender bartender1 = new Bartender();
		bartender1.setId(2L);
		bartender1.setFirstname("Uros");
		bartender1.setLastname("Stanojevic");
		bartender1.setMail("uros@gmail.com");
		bartender1.setPassword("sss");
		bartender1.setRegistrated("1");
		bartender1.setClothesSize(ClothesSize.XL);
		bartender1.setShoesSize(ShoesSize.no46);
		bartender1.setDefaultShift(DefaultShift.First);
		
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
		bartender.setId(4L);
		bartender.setFirstname("Basara");
		bartender.setLastname("Stanojevic");
		bartender.setMail("basara@gmail.com");
		bartender.setPassword("aaaa");
		bartender.setRegistrated("1");
		bartender.setClothesSize(ClothesSize.XL);
		bartender.setShoesSize(ShoesSize.no46);
		bartender.setDefaultShift(DefaultShift.First);
		
		ArrayList<Bartender> list = new ArrayList<Bartender>();
		list.add(bartender1);
		list.add(bartender2);
		list.add(bartender3);
		list.add(bartender);

		Page<Bartender> pageConfigurations = new PageImpl<Bartender>(list);
		return pageConfigurations;
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
 
}
