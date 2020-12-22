package cyfer;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CyferApplication {

	/*
	 * UC1 - Pregled profila udruga i pasa iz te udruge-boki
	 * UC2 - Pregled liste profila svih pasa-*
	 * UC3 - Pregled statistike svih pasa-*
	 * UC4 - Pregled rang-liste svih setaca-dave
	 * UC5 - Registracija korisnika (gradanina ili udruge) u sustav-*
	 * UC6 - Prijava korisnika (gradanina ili udruge) u sustav-*
	 * UC7 - Pregled osobnih podataka korisnika (udruge ili gradanina)-*
	 * UC8 - Promjena osobnih podataka korisnika-*
	 * UC9 - Brisanje korisnickog racuna (udruge ili gradanina)-*
	 * UC10 - Dodavanje novog profila psa u listu prijavljene udruge-sven
	 * UC11 - Uredivanje profila psa neke udruge-boki
	 * UC12 - Brisanje profila psa iz liste neke udruge-dave
	 * UC13 - Rezervacija termina setnje-*
	 * UC14 - Pregled vlastitih statistika setnji-sven
	 * UC15 - Oznacavanje statistike setnji kao javne -sven
	 * UC16 - Pregled i skidanje (eng.download) kalendara registriranog gradanina-dave
	 * UC17 - Pregled korisnika-zaba
	 * UC18 - Brisanje korisnika-zaba
	 */

	@Bean
	public PasswordEncoder pswdEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(CyferApplication.class, args);
	}
}
