package cyfer.domain;

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

@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@GeneratedValue
	@Column(unique = true)
	private Long reservationId;
	
	@ManyToOne(targetEntity = Walk.class)
	@JoinColumn(name = "walkId")
	private Walk walk;

	@ManyToOne(targetEntity = Walker.class)
	@JoinColumn(name = "walkerId")
	private Walker walker;

	@ManyToOne(targetEntity = Dog.class)
	@JoinColumn(name = "dogId")
	private Dog dog;

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

	public Reservation(Walk walk, Walker walker, Dog dog) {
		super();
		this.walk = walk;
		this.walker = walker;
		this.dog = dog;
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
