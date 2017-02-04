package app.dish;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DISH_ID")
	private Long id;

	@NotNull
	@Column
	private String name;

	@Column
	private String text;

	@NotNull
	@Column
	@Min(0)
	private Long price;

	@NotNull
	@Column
	@Min(0)
	private Integer count;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private TypeOfDish typeOfDish;

	@Column
	private Integer summRate;

	@Column
	private Integer numRate;
}