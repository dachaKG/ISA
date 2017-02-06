package app.manager.changedShift;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
@Transactional
public class ChangedShiftServiceImpl implements ChangedShiftService {
	private final ChangedShiftRepository repository;

	@Autowired
	public ChangedShiftServiceImpl(final ChangedShiftRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ChangedShift> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public ChangedShift save(ChangedShift changedShift) {
		return repository.save(changedShift);
	}

	@Override
	public ChangedShift findOne(Long id) {
		return repository.findOne(id);
	}
}