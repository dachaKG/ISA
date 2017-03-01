package app.employed.bartender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
public class BartenderServiceTest {
	
	@Autowired
	private BartenderRepository repository;
	
	@Autowired
	BartenderService bartenderService;
	

	@Test
	public void testFindAll() {
		List<Bartender> bartenders = (List<Bartender>) repository.findAll();
		assertEquals(3,bartenders.size());
	}

	@Test
	public void testFindOne() {
		Bartender bartender = repository.findOne(1L);
		assertNotNull(bartender);
		assertEquals("a@gmail.com", bartender.getMail());
	}

	@Test
	public void testSave() {
		Bartender bartenderTemp = getBartender();
		Bartender bartender = repository.save(bartenderTemp);
		assertEquals(bartender.getMail(), bartenderTemp.getMail());
	}

	@Test
	public void testDelete() {
		repository.delete(3L);
		assertNull(repository.findOne(3L));
	}

	@Test
	public void testFindOneMailAndPass() {
		String mail = "a@gmail.com";
		String password = "a";
		Bartender bartender = bartenderService.findOneMailAndPass(mail, password);
		assertEquals(bartender.getMail(), mail);
		assertEquals(bartender.getPassword(), password);
	}

	@Test
	public void testFindOneWithMail() {
		String mail = "a@gmail.com";
		Bartender bartender = bartenderService.findOneWithMail(mail);
		assertEquals(bartender.getMail(),mail);
	}
	
	private Bartender getBartender(){
		Bartender bartender = new Bartender();
		//bartender.setId(1L);
		bartender.setFirstname("Danilo");
		bartender.setLastname("Dimitrijevic");
		bartender.setMail("vlado94@gmail.com");
		bartender.setPassword("ssss");
		bartender.setRegistrated("1");
		bartender.setClothesSize(ClothesSize.XL);
		bartender.setShoesSize(ShoesSize.no46);
		bartender.setDefaultShift(DefaultShift.First);
		//bartender.setOrders(getOrders());
		return bartender;
	}

}
