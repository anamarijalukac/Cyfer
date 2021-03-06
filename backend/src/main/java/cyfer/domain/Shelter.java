package cyfer.domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shelter")
public class Shelter {
	
	public Shelter() {
	}
	
	@Id
	@GeneratedValue
	@Column
	private Long shelterId;

	@Column(unique = true)
	@NotNull
	@Size(min = 11, max = 11)
	private String OIB;
	
	@Column
	@NotNull
	private String name;
	
	@Column(unique = true)
	@NotNull
	private String username;

	@Column
	@NotNull
	private String password;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locationId")
    private Location location;
	@Column
	private String city;
	@Column
	private String address;


	@Column
	private String image;

	public Long getShelterId() {
		return shelterId;
	}


	public String getOIB() {
		return OIB;
	}

	public Location getLocation() {
		return location;
	}

	public void setOIB(String oib) {
		this.OIB = oib;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setLocation(Location l) {
		this.location=l;
		this.address=l.getAddress();
		this.city=l.getCity();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

    public void setShelterId(long id) {
		this.shelterId = id;
    }

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Shelter shelter = (Shelter) o;
		return Objects.equals(shelterId, shelter.shelterId) &&
				Objects.equals(OIB, shelter.OIB) &&
				Objects.equals(name, shelter.name) &&
				Objects.equals(username, shelter.username) &&
				Objects.equals(password, shelter.password) &&
				Objects.equals(location, shelter.location) &&
				Objects.equals(city, shelter.city) &&
				Objects.equals(address, shelter.address) &&
				Objects.equals(image, shelter.image);
	}

	@Override
	public int hashCode() {
		return Objects.hash(shelterId, OIB, name, username, password, location, city, address, image);
	}
}
