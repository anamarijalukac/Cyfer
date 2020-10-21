package cyfer.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shelter")
public class Shelter {
	
	public Shelter() {}	
	@Id
	@Column(name="shelterOIB")
	private String shelterOIB;
	
	@Column(name="shelterName")
	private String shelterName;
	
	@Column(name="shelterPassword")
	private String shelterPassword;
	
	@Column(name="shelterUsername")
	private String shelterUsername;
	
	public Shelter(String shelterName, String shelterOIB) {
		this.shelterOIB = shelterOIB;
		this.shelterName = shelterName;
	}

}
