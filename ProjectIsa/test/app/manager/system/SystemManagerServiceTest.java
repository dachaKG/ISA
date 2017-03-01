package app.manager.system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemManagerServiceTest {
	@Autowired
	SystemManagerRepository repository;

	@Autowired
	private SystemManagerService systemManagerService;
	
	
	@Test
	public void findAllTest() {
		List<SystemManager> managers = systemManagerService.findAll();
		assertThat(managers.size()).isGreaterThan(1);
	}
	
	@Test
	public void saveTest() {
		SystemManager managerForsave = getSystemManager();
		SystemManager manager =  repository.save(managerForsave);
		assertEquals(manager.getMail(),managerForsave.getMail());
	}

	@Test
	public void findOneTest() {
		SystemManager manager =  repository.findOne(1l);
		assertNotNull(manager);
	}

	@Test
	public void findOneTestByMailAndPassword() {
		String mail = "2";
		String password = "2";
		SystemManager manager =  repository.findByMailAndPassword(mail,password);
		assertEquals(manager.getMail(),mail);
		assertEquals(manager.getPassword(),password);
	}
	
	@Test
	public void findOneWithMailTest() {
		String mail = "2";
		SystemManager manager =  repository.findByMail(mail);
		assertEquals(manager.getMail(),mail);
	}

	@Test
	public void deleteTest() {
		repository.delete(2l);
		assertNull(repository.findOne(2l));
	}
	private SystemManager getSystemManager() {
		SystemManager systemManager = new SystemManager();	
		systemManager.setFirstname("aaa");
		systemManager.setLastname("bbb");
		systemManager.setMail("1@1");
		systemManager.setPassword("ppp");
		systemManager.setRegistrated("1");
		return systemManager;
	}
}
