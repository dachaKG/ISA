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
}
