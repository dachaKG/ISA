package app.users;

import java.util.List;

public interface UserService {
	List<User> findAll();

	User save(User user);

	User findOne(Long id);

	void delete(Long id);
}
