package app.bidder;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import app.common.User;
import app.order.Orderr;
import lombok.Data;

@Data
@Entity
public class Bidder extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BIDDER_ID")
	private Long id;

	// lista svih dosadasnjih porudzbina
	@ManyToMany
	@JoinTable(name = "BIDDER_ORDERR", joinColumns = @JoinColumn(name = "BIDDER_ID"), inverseJoinColumns = @JoinColumn(name = "ORDERR_ID"))
	private List<Orderr> lastOrders;

}
