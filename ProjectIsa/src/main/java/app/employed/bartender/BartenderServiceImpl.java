package app.employed.bartender;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

public class BartenderServiceImpl implements BartenderService{

	private final BartenderRepository repository;
	
	@Autowired
	public BartenderServiceImpl(final BartenderRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Bartender> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Bartender findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Bartender save(Bartender bartender) {
		return repository.save(bartender);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}
