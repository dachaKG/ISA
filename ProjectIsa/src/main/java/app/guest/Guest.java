package app.guest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import app.common.User;
import lombok.Data;

@Data
@Entity
public class Guest extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GUEST_ID")
	private Long id;

	@Column
	@NotNull
	private String temp;
}
