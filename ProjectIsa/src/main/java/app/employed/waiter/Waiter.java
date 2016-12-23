package app.employed.waiter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import app.employed.Employed;
import app.order.Orderr;
import app.restaurant.Restaurant;
import app.restaurant.Segment;
import lombok.Data;

@Data
@Entity
public class Waiter extends Employed {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "WAITER_ID")
	private Long id;

	@OneToMany
	@JoinTable(name = "WAITER_ORDERS", joinColumns = @JoinColumn(name = "WAITER_ID"), inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
	private List<Orderr> orders;

	//@OneToOne(cascade = CascadeType.MERGE)
	//@JoinColumn(name = "RESTAURANT_ID")
	@ManyToOne
	private Restaurant restaurant;

	@ManyToOne
	@JoinTable(name = "WAITER_SEGMENT", joinColumns = @JoinColumn(name = "WAITER_ID"), inverseJoinColumns = @JoinColumn(name = "SEGMENT_ID"))
	private Segment segment;
}