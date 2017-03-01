package app.employed.waiter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.employed.ClothesSize;
import app.employed.DefaultShift;
import app.employed.ShoesSize;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaiterServiceTest {
	
	@Autowired
	private WaiterRepository repository;
	
	@Autowired
	private WaiterService waiterService;

	@Test
	public void testFindAll() {
		List<Waiter> waiters = (List<Waiter>) repository.findAll();
		assertEquals(5,waiters.size());
	}

	@Test
	public void testSave() {
		Waiter waiter = new Waiter();
		waiter.setFirstname("Pera");
		waiter.setLastname("Peric");
		waiter.setMail("rrr@sss");
		waiter.setPassword("sifra");
		waiter.setRegistrated("0");
		waiter.setClothesSize(ClothesSize.S);
		waiter.setShoesSize(ShoesSize.no40);
		waiter.setDefaultShift(DefaultShift.First);
		waiter.setRate(2.2);
		waiter.setNumRate(new ArrayList<Integer>());
		Waiter saveWaiter = repository.save(waiter);
		assertEquals(saveWaiter.getMail(), waiter.getMail());
	}

	@Test
	public void testFindOneLong() {
		Waiter waiter = repository.findOne(1L);
		assertNotNull(waiter);
		assertEquals("9@9", waiter.getMail());
	}

	@Test
	public void testDelete() {
		repository.delete(5L);
		assertNull(repository.findOne(5L));
	}

	@Test
	public void testFindOneStringString() {
		String mail = "9@9";
		String password = "9";
		Waiter waiter = waiterService.findOne(mail, password);
		assertEquals(waiter.getMail(), mail);
		assertEquals(waiter.getPassword(), password);
	}

	@Test
	public void testFindOneWithMail() {
		String mail = "9@9";
		Waiter waiter = waiterService.findOneWithMail(mail);
		assertEquals(waiter.getMail(), mail);
	}

}
