package app.guest;

import java.util.List;

public interface GuestService {
	List<Guest> findAll();

	Guest save(Guest guest);

	Guest findOne(Long id);

	Guest findOne(String mail,String password);
	
	Guest findOneWithMail(String mail);
	
	void activate(String regNum);
	
	void delete(Long id);
	
	List<Guest> findByFirstAndLastName(String inputStr);
}
