package cyfer.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@GeneratedValue
	@Column
	private Long reservationId;
	
	@ManyToOne(targetEntity = Walk.class)
	@JoinColumn(name = "walkId")
	private Walk walk;
	@Column
	@DateTimeFormat(iso = ISO.DATE_TIME) 
	private Timestamp dateTime;
	@Column
	private int duration;

	@ManyToOne(targetEntity = Walker.class)
	@OnDelete(action = OnDeleteAction.CASCADE )
	@JoinColumn(name = "walkerId")
	private Walker walker;
	@Column
	private String firstName;	
	@Column
	private String lastName;
	@Column
	private String username;
	@Column
	private String email;
	

	@ManyToOne(targetEntity = Dog.class)
	@JoinColumn(name = "dogId")
	private Dog dog;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	@Size(min = 1, max = 1)
	private String typeOfWalk;
	
	

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Walker getWalker() {
		return walker;
	}

	public void setWalker(Walker walker) {
		this.walker = walker;
	}

	public Reservation() {
	}

	public int getDuration() {
		return duration;
	}

	public Reservation(Walk walk, Walker walker, Dog dog) {
		super();
		this.walk = walk;
		this.dateTime=walk.getDateTime();
		this.duration=walk.getDuration();
		
		this.walker = walker;
		this.firstName=walker.getFirstName();
		this.lastName=walker.getLastName();
		this.username=walker.getUsername();
		this.email=walker.getEmail();
		
		this.dog = dog;
		this.name=dog.getName();
		this.description=dog.getDescription();
		this.typeOfWalk=dog.getTypeOfWalk();
	}

	

	public Walk getWalk() {
		return walk;
	}

	public void setWalk(Walk walk) {
		this.walk = walk;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

}
