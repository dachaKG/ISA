package app.guest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.bidder.Bidder;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestServiceTest {
	
	@Autowired
	private GuestService guestService;
	
	@Autowired
	private GuestRepository guestRepository;

	@Test
	public void findAlltest() {
		List<Guest> guests = guestService.findAll();
		assertThat(guests.size()).isGreaterThan(4);
	}
	
	
	@Test
	public void saveTest() {
		Guest guestForsave = getGuest();
		Guest guest =  guestRepository.save(guestForsave);
		assertEquals(guest.getMail(),guestForsave.getMail());
	}
	
	
	@Test
	public void findOneByPassAndMailTest() {
		String mail = "guest1";
		String password = "pass1";
		Guest guest =  guestRepository.findByMailAndPassword(mail,password);
		assertEquals(guest.getMail(),mail);
		assertEquals(guest.getPassword(),password);
	}
	
	@Test
	public void findByMail(){
		String mail = "guest1";
		Guest guest = guestRepository.findByMail(mail);
		assertEquals(mail, guest.getMail());
	}
	
	@Test
	public void findOne(){
		Long id = 1l;
		Guest guest = guestRepository.findOne(id);
		assertEquals("guest1", guest.getMail());
	}
	
	@Ignore
	@Test
	public void activateTest(){
		Guest guest = getGuest();
		guest.setRegistrated("12345");
		guestService.activate("12345");
		assertEquals("1", guestService.findOne(3l).getRegistrated());
	}
	
	
	@Test
	public void findByFirstAndLastName(){
		String inputStr = "mika";
		Guest guest =  (Guest) guestRepository.findByFirstnameContaining(inputStr).get(0);
		assertEquals(inputStr,guest.getFirstname());
	}
	
	
	
	
	
	private Guest getGuest() {
		Guest guest = new Guest();	
		guest.setFirstname("gg1");
		guest.setLastname("prezim");
		guest.setMail("gost@g.gg");
		guest.setPassword("g");
		guest.setRegistrated("1");
		return guest;
	}	

}
