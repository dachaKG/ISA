package app.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.reservation.Reservation;
import app.reservation.ReservationRepository;
import app.reservation.ReservationService;
import app.reservation.ReservationServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

	@Autowired
	ReservationService service;
	
	@Autowired
	ReservationRepository repository;
	
	

	@Test
	public void findAlltest() {
		List<Reservation> reservations = service.findAll();
		assertThat(reservations.size()).isGreaterThan(5);
	}
	
	
	@Test
	public void findOne(){
		Long id = 1l;
		Reservation res = repository.findOne(id);
		assertEquals(id, res.getId());
	}

}
