package app.employed.cook;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import app.order.Orderr;
import lombok.Data;

@Data
@Entity
@IdClass(CookOrder.class)
public class CookOrder implements Serializable {
		

	@Id
	@ManyToOne
	//@PrimaryKeyJoinColumn(name="COOK_ORDER_ID", referencedColumnName="COOK_ID")
	private Cook cook;
	
	@Id
	@ManyToOne
	//@PrimaryKeyJoinColumn(name="ORDER_COOK_ID", referencedColumnName="ORDER_ID")
	private Orderr order;
	
	@Enumerated(EnumType.STRING)
	@Column
	private DishStatus dishStatus;

}
