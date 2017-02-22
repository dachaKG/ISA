package app.employed.cook;

import java.util.List;

public interface CookOrderService {
	List<CookOrder> findAll();

	CookOrder save(CookOrder cookOrder);

	CookOrder findOne(Long id);

	void delete(Long id);
}
