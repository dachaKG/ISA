package app.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.reservation.Reservation;
import app.reservation.ReservationRepository;
import app.reservation.ReservationService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TableServiceTest {

	
	@Autowired
	TableService service;
	
	@Autowired
	TableRepository repository;
	
	
	
	
	@Test
	public void findAlltest() {
		List<Table> ts = service.findAll();
		assertThat(ts.size()).isGreaterThan(5);
	}
	
	
	@Test
	public void findOne(){
		Long id = 1l;
		Table t = repository.findOne(id);
		assertEquals(id, t.getId());
	}

}
