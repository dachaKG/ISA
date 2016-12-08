package app.dish;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

import app.restaurant.Restaurant;
import lombok.Data;

@Data
@Entity
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DISH_ID")
	private Long id;

	@Column
	private String name;

	@Column
	private String text;

	@Column
	private Integer price;

	@Column
	private Integer summRate;

	@Column
	private Integer numRate;
	
	@JsonBackReference
	// @org.codehaus.jackson.annotate.JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RESTAURANT_ID")
	private Restaurant restaurant;
}