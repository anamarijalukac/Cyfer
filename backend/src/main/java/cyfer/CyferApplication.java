package cyfer;

import cyfer.rest.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CyferApplication {

    /*
     * UC1 - Pregled profila udruga i pasa iz te udruge-*
     * UC2 - Pregled liste profila svih pasa-*
     * UC3 - Pregled statistike svih pasa-*
     * UC4 - Pregled rang-liste svih setaca-dave (SORT SAMO)
     * UC5 - Registracija korisnika (gradanina ili udruge) u sustav-*
     * UC6 - Prijava korisnika (gradanina ili udruge) u sustav-*
     * UC7 - Pregled osobnih podataka korisnika (udruge ili gradanina)-*
     * UC8 - Promjena osobnih podataka korisnika-*
     * UC9 - Brisanje korisnickog racuna (udruge ili gradanina)-*
     * UC10 - Dodavanje novog profila psa u listu prijavljene udruge-*
     * UC11 - Uredivanje profila psa neke udruge-*
     * UC12 - Brisanje profila psa iz liste neke udruge-*
     * UC13 - Rezervacija termina setnje-*
     * UC14 - Pregled vlastitih statistika setnji-sven
     * UC15 - Oznacavanje statistike setnji kao javne -sven
     * UC16 - Pregled i skidanje (eng.download) kalendara registriranog gradanina-*
     * UC17 - Pregled korisnika-zaba?
     * UC18 - Brisanje korisnika-zaba?
     */

    @Bean
    public PasswordEncoder pswdEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(CyferApplication.class, args);
    }
}
