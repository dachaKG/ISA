package app.restaurant;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import app.dish.Dish;
import app.drink.Drink;
import app.manager.restaurant.RestaurantManager;
import lombok.Data;

@Data
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESTAURANT_ID")
	private Long id;

	@Column
	@NotNull
	private String name;

	@NotNull
	@OneToOne
	@JoinColumn(name = "RESTAURANT_MANAGER_ID")
	private RestaurantManager restaurantManager;

	@ManyToMany
	@JoinTable(name = "RESTAURANT_DISH", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
	private List<Dish> food;

	@ManyToMany
	@JoinTable(name = "RESTAURANT_DRINK", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "DRINK_ID"))
	private List<Drink> drinks;

	@ManyToMany
	@JoinTable(name = "RESTAURANT_SEGMENT", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "SEGMENT_ID"))
	private List<Segment> segments;

	@Column
	private Integer summRate = 0;

	@Column
	private Integer numRate = 0;
}