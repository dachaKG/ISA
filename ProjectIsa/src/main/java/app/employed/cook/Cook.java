package app.employed.cook;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import app.employed.Employed;
import app.order.Orderr;
import app.restaurant.Restaurant;
import lombok.Data;


@Data
@Entity
public class Cook extends Employed{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COOK_ID")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	//@NotNull
	@Column
	private DishStatus dishStatus;
	
	@OneToMany
	@JoinTable(name = "COOK_ORDERS", joinColumns = @JoinColumn(name = "COOK_ID"), inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
	private List<Orderr> orders;
	
	@ManyToOne
	@JoinTable(name = "COOK_RESTAURANT", joinColumns = @JoinColumn(name = "COOK_ID"), inverseJoinColumns = @JoinColumn(name = "RESTAURANT_ID"))
	private Restaurant restaurant;
	
}
