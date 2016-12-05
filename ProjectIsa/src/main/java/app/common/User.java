package app.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@MappedSuperclass
public class User {
	
	@NotBlank
	@Column(name="mail", unique=true)
	private String mail;
	
	@NotBlank
	@Column
	private String password;

	@NotBlank
	@Column
	private String firstname;

	@NotBlank
	@Column
	private String lastname;
	
	//flag koji je inicijalno false, i kad korisnik klikne na link pri registraciji postaje true
	
	@Column
	private boolean registrated;
}
