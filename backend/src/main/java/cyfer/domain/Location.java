package cyfer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Location {
	
	@Id
	@GeneratedValue
	@Column(unique = true)
	private Long locationId;
	
	@Column
	private String city;
	
	@Column
	private String address;
	
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
	
	public Long getId() {
		return locationId;
	}
	
	public void setId(long id) {
		this.locationId = id;
	}
	
}
