package app.employed.cooker;

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
import app.employed.cook.Cook;
import app.employed.cook.CookRepository;
import app.employed.cook.CookService;
import app.employed.cook.TypeOfCooker;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CookServiceTest {
	
	@Autowired
	private CookRepository repository;
	
	@Autowired
	CookService cookService;
	

	@Test
	public void testFindAll() {
		List<Cook> cooks = (List<Cook>) repository.findAll();
		assertEquals(3,cooks.size());
	}

	@Test
	public void testSave() {
		Cook cookTemp = getCook();
		Cook cook = repository.save(cookTemp);
		assertEquals(cook.getMail(), cookTemp.getMail());
	}

	@Test
	public void testFindOneLong() {
		Cook cook = repository.findOne(2L);
		assertNotNull(cook);
		assertEquals("8@8", cook.getMail());
	}

	@Test
	public void testFindOneStringString() {
		String mail = "8@8";
		String password = "8";
		Cook cook = cookService.findOne(mail, password);
		assertEquals(cook.getMail(), mail);
		assertEquals(cook.getPassword(), password);
	}

	@Test
	public void testDelete() {
		repository.delete(3L);
		assertNull(repository.findOne(3L));
	}

	@Test
	public void testFindOneWithMail() {
		String mail = "8@a";
		Cook cook = cookService.findOneWithMail(mail);
		assertEquals(cook.getMail(),mail);
	}
	
	private Cook getCook(){
		Cook cook = new Cook();
		cook.setFirstname("mika");
		cook.setLastname("mikic");
		cook.setMail("ss@ss");
		cook.setPassword("sifta");
		cook.setRegistrated("0");
		cook.setClothesSize(ClothesSize.M);
		cook.setShoesSize(ShoesSize.no39);
		cook.setDefaultShift(DefaultShift.Second);
		cook.setTypeOfCooker(TypeOfCooker.baked);
		return cook;
		
	}

}
