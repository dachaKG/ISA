package app.guest;

import java.util.List;

public interface GuestService {
	List<Guest> findAll();

	Guest save(Guest guest);

	Guest findOne(Long id);

	Guest findOne(String mail,String password);
	
	Guest findOneWithMail(String mail);
	
	void delete(Long id);
}
