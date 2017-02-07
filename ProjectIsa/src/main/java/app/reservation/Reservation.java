package app.reservation;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import app.guest.Guest;
import lombok.Data;

@Data
@Entity
public class Reservation {
	
	public Reservation(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESERVATION_ID")
	private Long id;
	
	@Column
	private Date date;
	
	@Column
	private int hours;
	
	@Column
	private int minuts;
	
	@Column
	private Double duration;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RESERVATION_GUEST", joinColumns = @JoinColumn(name = "RESERVATION_ID"), inverseJoinColumns = @JoinColumn(name = "GUEST_ID"))
	private List<Guest> guests;

}
