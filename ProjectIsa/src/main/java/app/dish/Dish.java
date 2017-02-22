package app.dish;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	
	@Enumerated(EnumType.STRING)
	@Column
	private DishStatus dishStatus;

	@Column
	private double rate;

/*	@ManyToMany
	@JoinTable(name = "DISH_NUMRATES", joinColumns = @JoinColumn(name = "DISH_ID"))*/
	@ElementCollection
	private List<Integer> numRate;
	
	public double getRate(){
		double sum = 0;
		double average = 0;
		
		if(this.numRate.size() > 0){
			for(int i = 0 ; i < this.numRate.size(); i++){
				sum += numRate.get(i);
			}
			average = sum/this.numRate.size();
		}
		return average;
	}
}