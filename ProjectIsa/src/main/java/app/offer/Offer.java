package app.offer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import app.bidder.Bidder;
import lombok.Data;

@Data
@Entity
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OFFER_ID")
	private Long id;
	
	@NotNull
	@Column
	private Integer price;

	@OneToOne(cascade = CascadeType.ALL)
	private Bidder bidder;
	
	//0 -active 1--acctepted 2--rejected
	@NotBlank
	@Column
	private String accepted;
}
