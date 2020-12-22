package cyfer.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "walk")
public class Walk {

	public Walk() {}
	
	@Id
	@GeneratedValue
	@Column(unique = true)
	private Long walkId;
	
	@Column
	@DateTimeFormat(iso = ISO.DATE_TIME) 
	private Timestamp dateTime;

	@Column
	private int duration;
	
	
	
	
	


	public Long getWalkId() {
		return walkId;
	}

	public void setWalkId(Long walkId) {
		this.walkId = walkId;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
