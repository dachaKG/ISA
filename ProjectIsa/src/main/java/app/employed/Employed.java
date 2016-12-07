package app.employed;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import app.common.User;
import app.order.Order;
import app.restaurant.Restaurant;
import lombok.Data;

@Data
@Entity(name = "EMPLOYED")
public class Employed extends User{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPLOYED_ID")
	private Long id;
	
	@ManyToMany
	@JoinTable(name = "EMPLOYED_ORDERS", joinColumns = @JoinColumn(name = "EMPLOYED_ID"), inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
	private List<Order> orders;
	
	//Treba organizovati i raspored rada
 //  private Date datumRodjenja;
   @Column
   private String konfekcijskiBroj;
   
   @Column
   private int velicinaObuce;
   
   //@ManyToOne(cascade=CascadeType.ALL) 
   //public Restaurant restoran;
	
}
