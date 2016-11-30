package app.component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@XmlRootElement(name="componentType")
@XmlAccessorType (XmlAccessType.FIELD)
public class ComponentType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "type_id")
	private Long id;

	@Column(name = "component_type_name")
	@NotEmpty
	private String name;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Cardinality cardinality;
}
