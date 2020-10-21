package cyfer.domain;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="walk")
@IdClass(WalkId.class)
public class Walk implements Serializable {
	
	public Walk() {}
	
	@Id
	@Column(name="walkDate_n_time")
	private Timestamp walkDate_n_time;
	
	@Column(name="walkDuration")
	private int walkDuration;
	
	@Id
	@Column(name="dogId")
	private String dogId;
	
	@Id
	@Column(name="personUsername")
	private String personUsername;
	
	public Walk(Timestamp walkDate_n_time, int walkDuration, String personUsername, String dogId) {
		this.walkDate_n_time = walkDate_n_time;
		this.walkDuration = walkDuration;
		this.personUsername = personUsername;
		this.dogId = dogId;
	}
	
	public Timestamp getTimestamp() {
		return walkDate_n_time;
	}

	public int getOIB() {
		return walkDuration;
	}

	public String dogId() {
		return dogId;
	}
	
	public String getUsername() {
		return personUsername;
	}

}
