package app.drink;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Drink {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DRINK_ID")
	private Long id;

	@Column
	private String name;

	@Column
	private String text;

	@Column
	private Integer price;

	@Column
	private Integer count;
	
	@Column
	private Integer summRate;

	@Column
	private Integer numRate;

	/*
	@ManyToOne
	@JoinColumn(name = "RESTAURANT_ID")
	private Restaurant restaurant;*/
}