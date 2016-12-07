package app.guest;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import app.common.User;
import app.restaurant.Restaurant;
import lombok.Data;

@Data
@Entity
public class Guest extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GUEST_ID")
	private Long id;

	//lista restorana gde je sve bio 
	@ManyToMany
	@JoinTable(name = "GUEST_RESTAURANT", joinColumns = @JoinColumn(name = "GUEST_ID"), inverseJoinColumns = @JoinColumn(name = "RESTAURANT_ID"))
	private List<Restaurant> visitedRestaurants;

}