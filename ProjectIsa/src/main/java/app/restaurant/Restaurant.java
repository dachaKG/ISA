package app.restaurant;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import app.bidder.Bidder;
import app.dish.Dish;
import app.drink.Drink;
import app.employed.bartender.Bartender;
import app.employed.cook.Cook;
import app.employed.waiter.Waiter;
import app.manager.restaurant.RestaurantManager;
import app.order.Orderr;
import lombok.Data;

@Data
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESTAURANT_ID")
	private Long id;

	@Column
	@NotBlank
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RESTAURANT_MANAGERS_OF_RESTAURANT", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "RESTAURANT_MANAGER_ID"))
	private List<RestaurantManager> restaurantManagers;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RESTAURANT_DISH", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
	private List<Dish> food;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RESTAURANT_DRINK", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "DRINK_ID"))
	private List<Drink> drinks;

	@OneToMany(mappedBy = "restaurant")
	private List<Segment> segments;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RESTAURANT_WAITERS", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "WAITER_ID"))
	private List<Waiter> waiters;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RESTAURANT_COOKS", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "COOK_ID"))
	private List<Cook> cooks;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RESTAURANT_BARTENDERS", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "BARTENDER_ID"))
	private List<Bartender> bartenders;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RESTAURANT_BIDDERS", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "BIDDER_ID"))
	private List<Bidder> bidders;

	@Column
	private Integer summRate;

	@Column
	private Integer numRate;

	@OneToMany
	@JoinTable(name = "RESTAURANT_ORDER", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
	private List<Orderr> order;
	
	@Column
	@NotBlank
	private String country;

	@Column
	@NotBlank
	private String city;
	
	@Column
	@NotBlank
	private String street;
	
	@Column
	@NotBlank
	private String number;


	
}