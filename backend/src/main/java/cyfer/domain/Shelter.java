package cyfer.domain;

import java.util.List;

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
	
	@Column
	@NotNull
	private String username;

	@Column
	@NotNull
	private String password;

	@ManyToOne(targetEntity = Location.class)
	@JoinColumn(name = "locationId")
    private Location location;
	@Column
	private String address;
	@Column
	private String city;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Long getShelterId() {
		return shelterId;
	}

	public void setShelterId(Long shelterId) {
		this.shelterId = shelterId;
	}

	public String getOIB() {
		return OIB;
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

}
