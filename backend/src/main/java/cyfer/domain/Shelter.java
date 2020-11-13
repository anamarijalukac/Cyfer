package cyfer.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shelter")
public class Shelter {
	
	public Shelter() {
	}
	
	@Column
	@Id
	@GeneratedValue
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
	private String password;

	@Column
	@NotNull
	private String username;

	
	@OneToOne(mappedBy = "shelter", optional = false)
    private Location location;

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
