package app.guest;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {
	private final GuestRepository repository;

	@Autowired
	public GuestServiceImpl(final GuestRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Guest> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Guest save(Guest guest) {
		return repository.save(guest);
	}

	@Override
	public Guest findOne(Long id) {
		return repository.findOne(id);
	}

	// ovo se kasnije na repo spusta
	@Override
	public Guest findOne(String mail, String password) {
		List<Guest> guests = (List<Guest>) repository.findAll();
		for (int i = 0; i < guests.size(); i++) {
			if (guests.get(i).getMail().equals(mail) && guests.get(i).getMail().equals(password))
				return guests.get(i);
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Guest findOneWithMail(String mail) {
		List<Guest> list = findAll();
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMail().equals(mail))
				return list.get(i);
		return null;
	}
}
