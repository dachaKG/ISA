package app.order;

import java.util.List;

public interface OrderService {
	List<Order> findAll();

	Order save(Order order);

	Order findOne(Long id);

	void delete(Long id);
}
