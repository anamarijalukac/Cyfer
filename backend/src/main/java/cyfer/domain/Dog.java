package cyfer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Dog {

	@Id
	@GeneratedValue
	@Column(unique = true)
	private Long dogId;

	@Column(unique = true)
	@NotNull
	private String name;
	
	@Column
	private String description;

	@Column
	private String locationId;
	
	@Column
	@NotNull
	@Size(min = 1, max = 1)
	private String typeOfWalk;
	
	@Column
	private String shelterId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeOfWalk() {
		return typeOfWalk;
	}

	public void setTypeOfWalk(String typeOfWalk) {
		this.typeOfWalk = typeOfWalk;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLocationId() {
		return locationId;
	}

	public String getShelterId() {
		return shelterId;
	}

	@Column(unique = true, nullable = false)
	private String image;

	public Long getId() {
		return dogId;
	}
	
	public void setId(long id) {
		this.dogId = id;
	}

}
