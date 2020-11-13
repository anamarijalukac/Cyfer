package cyfer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "walker")
public class Walker {

	public Walker() {
	}

	@Id
	@GeneratedValue
	@Column
	private Long walkerId;

	@Column
	@NotNull
	private String username;

	@Column
	@NotNull
	private String lastName;

	@Column
	@NotNull
	private String password;

	@Column(unique = true)
	@NotNull
	private String email;

	@Column
	@NotNull
	private String firstName;	
	
	
	
	@OneToMany(mappedBy="walker",cascade=CascadeType.ALL,orphanRemoval=true)
	List<Reservation> reservations=new ArrayList<>();
	
	

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Long getWalkerId() {
		return walkerId;
	}

	public void setWalkerId(Long walkerId) {
		this.walkerId = walkerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
