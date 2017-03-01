package app.restaurant;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RestaurantServiceTest {

	RestaurantService service;

	@Mock
	RestaurantRepository repository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new RestaurantServiceImpl(repository);
	}
	

	@Test
	public void testFindOne() {
		when(repository.findOne(1l)).thenReturn(getRestaurant());
		Restaurant restaurant = service.findOne(1l);
		verify(repository).findOne(1l);
		assertEquals(restaurant.getName(), "111");
	}
	
	@Test
	public void saveTest() {
		when(repository.save(getRestaurant())).thenReturn(getRestaurant());
		Restaurant restaurant = service.save(getRestaurant());
		verify(repository).save(getRestaurant());
		assertEquals(restaurant.getName(), getRestaurant().getName());
	}
	
	@Test
	public void findByNameAndTypeTest() {
		when(repository.findByNameContaining("a")).thenReturn(getRestaurants());
		when(repository.findByDescriptionContaining("a")).thenReturn(getRestaurants());
		List<Restaurant> list = service.findByNameAndType("a");
		verify(repository).findByNameContaining("a");
		verify(repository).findByDescriptionContaining("a");
		assertEquals(list.size(), 4);
	}
	

	
	private Restaurant getRestaurant() {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(1l);
		restaurant.setName("111");
		restaurant.setCountry("111");
		restaurant.setDescription("111");
		restaurant.setStreet("111");
		restaurant.setNumber("111");
		restaurant.setSummRate(0.0);
		restaurant.setRateRestaurant(new ArrayList<>());
		return restaurant;
	}
	
	private ArrayList<Restaurant> getRestaurants() {
		ArrayList<Restaurant> list = new ArrayList<>();
		Restaurant restaurant = new Restaurant();
		restaurant.setId(1l);
		restaurant.setName("a");
		restaurant.setCountry("111");
		restaurant.setDescription("111");
		restaurant.setStreet("111");
		restaurant.setNumber("111");
		restaurant.setSummRate(0.0);
		restaurant.setRateRestaurant(new ArrayList<>());
		
		Restaurant restaurant1 = new Restaurant();
		restaurant1.setId(2l);
		restaurant1.setName("a");
		restaurant1.setCountry("111");
		restaurant1.setDescription("111");
		restaurant1.setStreet("111");
		restaurant1.setNumber("111");
		restaurant1.setSummRate(0.0);
		restaurant1.setRateRestaurant(new ArrayList<>());
		list.add(restaurant);
		list.add(restaurant1);
		return list;
	}
}
