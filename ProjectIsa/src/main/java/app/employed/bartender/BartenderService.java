package app.employed.bartender;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public interface BartenderService {

	List<Bartender> findAll();

	Bartender findOne(Long id);

	Bartender save(Bartender bartender);

	void delete(Long id);

}
