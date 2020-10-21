package cyfer.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dog")
public class Dog {
	
	public Dog() {}	
	@Id
	@Column(name="dogId")
	private String dogId;
	
	@Column(name="dogDescription")
	private String dogDescription;
	
	@Column(name="dogAvailability")
	private String dogAvailability;
	
	@Column(name="dogLocation")
	private String dogLocation;
	
	@Column(name="dogTypeOfWalk")
	private String dogTypeOfWalk;
	
	@Column(name="dogName")
	private String dogName;
	
	@Column(name="shelterOIB")
	private String shelterOIB;

}
