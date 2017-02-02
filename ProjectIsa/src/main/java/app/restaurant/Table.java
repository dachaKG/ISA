package app.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import app.order.Orderr;
import lombok.Data;

@Data
@Entity
public class Table {
	
	public static final int NOT_EXISTS = 0;
	public static final int EXISTS = 1;
	public static final int RESERVED = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TABLE_ID")
	private Long id;
	
	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "SEGMENT_ID")
	private Segment segment;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Orderr order;
	
	@Column
	private int xPos;
	
	@Column 
	private int yPos;
	
	@Column
	private int status;
	
	public Table(String name, Segment segment, int xPos, int yPos, int status){
		this.name = name;
		this.segment = segment;
		this.xPos = xPos;
		this.yPos = yPos;
		this.status = status;
	}
	
}
