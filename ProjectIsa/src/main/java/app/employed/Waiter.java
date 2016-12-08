package app.employed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import app.common.User;
import lombok.Data;

@Data
@Entity()
public class Waiter extends User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //automatsko generisanje kljuca
	@Column(name = "WAITER_ID")
	private long id;
}