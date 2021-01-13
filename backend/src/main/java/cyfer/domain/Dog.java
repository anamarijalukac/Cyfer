package cyfer.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dog")
public class Dog {

	@Id
	@GeneratedValue
	@Column(unique = true)
	private Long dogId;

	@Column
	@NotNull
	private String name;

	@Column
	private String description;

	@Column
	private String image;

	@Column
	@NotNull
	@Size(min = 1, max = 1)
	private String typeOfWalk;


	@ManyToOne(targetEntity = Shelter.class)
	@JoinColumn(name = "shelterId")
	private Shelter shelter;

	@ManyToOne(targetEntity = Location.class)
	@JoinColumn(name = "locationId")
	private Location location;

	public Shelter getShelter() {
		return shelter;
	}

	public void setShelter(Shelter shelter) {
		this.shelter = shelter;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

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

	public Long getDogId() {
		return dogId;
	}

	public void setDogId(Long dogId) {
		this.dogId = dogId;
	}
	



}
