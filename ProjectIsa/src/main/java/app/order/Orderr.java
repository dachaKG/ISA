package app.order;
                           
import java.util.ArrayList;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import app.dish.Dish;
import app.drink.Drink;
import app.restaurant.Table;
import lombok.Data;

@Data
@Entity
public class Orderr {
	//int total = 0;
	public Orderr(){
		this.drinks = new ArrayList<Drink>();
		this.food = new ArrayList<Dish>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private Long id;
	
	/*@Column
	private boolean status;*/
	
	@Enumerated(EnumType.STRING)
	@Column
	private DishStatus dishStatus;
	
	@Enumerated(EnumType.STRING)
	@Column
	private DrinkStatus drinkStatus;
	
	@Column
	private int total;
	
	public int getTotal(){
		int total = 0;
		if(getDrinks().size() > 0){
			for(int i = 0 ; i < getDrinks().size(); i++){
				total += getDrinks().get(i).getPrice();
			}
		}
		
		if(getFood().size() > 0){
			for(int i = 0 ; i < getFood().size(); i++){
				total += getFood().get(i).getPrice();
			}
		}
		
		return total;
	}

	@ManyToMany
	@JoinTable(name = "ORDER_DRINKS", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "DRINK_ID"))
	private List<Drink> drinks;

	@ManyToMany
	@JoinTable(name = "ORDER_FOOD", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
	private List<Dish> food;
	/*
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinTable(name = "ORDER_RESTAURANT", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "RESTAURANT_ID"))
	private Restaurant restaurant;
	*/
	@ManyToOne
	@JoinTable(name = "ORDER_TABLE", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "TABLE_ID"))
	private Table table;
}
