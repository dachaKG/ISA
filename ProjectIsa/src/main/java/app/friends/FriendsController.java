package app.friends;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.guest.Guest;
import app.guest.GuestService;

@RestController
@RequestMapping("/friends")
public class FriendsController {
	private final FriendsService friendService;
	private final GuestService guestService;

	@Autowired
	public FriendsController(final FriendsService friendService, final GuestService guestService) {
		this.friendService = friendService;
		this.guestService = guestService;
	}

	@GetMapping
	public ResponseEntity<List<Friends>> findAll() {
		return new ResponseEntity<>(friendService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{idFriend}")
	@ResponseStatus(HttpStatus.OK)
	public void addFriend(@PathVariable Long idFriend) {
		Guest guest = Optional.ofNullable(guestService.findOne(idFriend))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		addGuestAsFriend(guest);
	}

	public void addGuestAsFriend(Guest guest) {
		// ovo za sad stoji, dok se ne utvrdi kako da odrzavam aktivnog jednog
		// gosta
		Guest home = guestService.findOne(1l);
		List<Friends> friends = friendService.findAll();

		for (int i = 0; i < friends.size(); i++)
			if (friends.get(i).getFriendReciveRequest().getId() == guest.getId()
					|| friends.get(i).getFriendSendRequest().getId() == guest.getId())
				// ovo se menja, znak je da ovaj prijetelj vec postoji tu
				throw new BadRequestException();
		friendService.save(new Friends(home, guest));
	}
}
