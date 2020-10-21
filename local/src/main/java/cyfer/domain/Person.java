package cyfer.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person {
	
	public Person() {}
	
	@Id
	@Column(name="personUsername")
	private String personUsername;
	
	@Column(name="personSurname")
	private String personSurname;
	
	@Column(name="personPassword")
	private String personPassword;
	
	@Column(name="personEmail")
	private String personEmail;
	
	@Column(name="personName")
	private String personName;
	
	public Person(String personSurname, String personName, String personEmail) {
		this.personName = personName;
		this.personEmail = personEmail;
		this.personSurname = personSurname;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return personName;
	}

	public String getSurname() {
		// TODO Auto-generated method stub
		return personSurname;
	}

	public String getUsername() {
		return personUsername;
	}

}
