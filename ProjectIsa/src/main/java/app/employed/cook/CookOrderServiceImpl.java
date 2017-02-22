package app.employed.cook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

public class CookOrderServiceImpl implements CookOrderService{

	private final CookOrderRepository repository;

	@Autowired
	public CookOrderServiceImpl(final CookOrderRepository repository) {
		this.repository = repository;
	}

	
	@Override
	public List<CookOrder> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public CookOrder save(CookOrder cookOrder) {
		return repository.save(cookOrder);
	}

	@Override
	public CookOrder findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}
