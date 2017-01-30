package app.restaurant.restaurantOrder;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import app.offer.Offer;
import lombok.Data;

@Data
@Entity
public class RestaurantOrderr {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESTAURANT_ORDER_ID")
	private Long id;


	@NotBlank
	@Column
	private String orderActive;
	
	@NotBlank
	@Column
	private Date startDate;

	@NotBlank
	@Column
	private Date endDate;
	
	@Column
	private Integer drinkId;
	
	@Column
	private Integer dishId;
	
	@NotBlank
	@Column
	private Integer count;
	
	@OneToMany
	@JoinTable(name = "OFFER", joinColumns = @JoinColumn(name = "RESTAURANT_ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "OFFER_ID"))
	private List<Offer> offers;
	
	@Column
	private Integer idFromChoosenBidder;
	

}
