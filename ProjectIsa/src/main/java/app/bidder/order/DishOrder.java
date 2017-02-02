package app.bidder.order;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import app.dish.Dish;
import lombok.Data;

@Data
@Entity
public class DishOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DISH_ORDER_ID")
	private Long id;

	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "DISH_ID")
	private Dish dish;

	@Column
	@Min(0)
	private Integer count;

}
