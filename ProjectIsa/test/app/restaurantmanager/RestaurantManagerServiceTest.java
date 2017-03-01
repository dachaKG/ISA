package app.restaurantmanager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import app.manager.restaurant.RestaurantManager;
import app.manager.restaurant.RestaurantManagerRepository;
import app.manager.restaurant.RestaurantManagerService;
import app.manager.restaurant.RestaurantManagerServiceImpl;

public class RestaurantManagerServiceTest {

	RestaurantManagerService service;

	@Mock
	RestaurantManagerRepository repository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new RestaurantManagerServiceImpl(repository);
	}
	
	@Test
	public void testFindOne() {
		when(repository.findOne(1l)).thenReturn(getManager());
		RestaurantManager manager = service.findOne(1l);
		verify(repository).findOne(1l);
		assertEquals(manager.getFirstname(), "111");
	}
	
	@Test
	public void saveTest() {
		when(repository.save(getManager())).thenReturn(getManager());
		RestaurantManager manager = service.save(getManager());
		verify(repository).save(getManager());
		assertEquals(manager.getFirstname(), "111");
	}
	
	@Test
	public void findByMailAndPasswordTest() {
		when(repository.findByMailAndPassword("","")).thenReturn(getManager());
		RestaurantManager manager = service.findByMailAndPassword("","");
		verify(repository).findByMailAndPassword("","");
		assertEquals(manager.getFirstname(), "111");
	}
	
	@Test
	public void findByMailTest() {
		when(repository.findByMail("")).thenReturn(getManager());
		RestaurantManager manager = service.findByMail("");
		verify(repository).findByMail("");
		assertEquals(manager.getFirstname(), "111");
	}
	
	private RestaurantManager getManager() {
		RestaurantManager restaurantManager1 = new RestaurantManager();
		restaurantManager1.setId(1l);
		restaurantManager1.setFirstname("111");
		return restaurantManager1;
	}
}
