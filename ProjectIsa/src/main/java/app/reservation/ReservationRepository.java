package app.reservation;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

}
