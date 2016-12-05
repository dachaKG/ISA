package app.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class User {
	@Column
	String mail;

	@Column
	String password;

	@Column
	private String firstname;

	@Column
	private String lastname;
	
	//flag koji je inicijalno false, i kad korisnik klikne na link pri registraciji postaje true
	@Column
	private boolean registrated;
}
