package app.manager.restaurant;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RestaurantManagerRepository extends PagingAndSortingRepository<RestaurantManager, Long>{

}
