package cyfer;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cyfer.domain.Dog;
import cyfer.domain.Location;
import cyfer.domain.Shelter;
import cyfer.domain.Walk;
import cyfer.domain.Walker;
import cyfer.service.IDogService;
import cyfer.service.IReservationService;
import cyfer.service.IShelterService;
import cyfer.service.IWalkService;
import cyfer.service.IWalkerService;

/**
 * Example component used to insert some test students at application startup.
 */

@Component
public class DataInitializer {

	@Autowired
	private IWalkerService walkerService;
	@Autowired
	private IDogService dogService;
	@Autowired
	private IWalkService walkService;
	@Autowired
	private IReservationService reservationService;
	@Autowired
	private IShelterService shelterService;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		
		

		Walker walker1 = new Walker();
		walker1.setEmail("pero.peric@gmail.com");
		walker1.setFirstName("Pero");
		walker1.setLastName("Peric");
		walker1.setPassword("12345671");
		walker1.setUsername("maliPerica");
		
		Walker walker2 = new Walker();
		walker2.setEmail("pero.peric2@gmail.com");
		walker2.setFirstName("Pero2");
		walker2.setLastName("Peric2");
		walker2.setPassword("12345672");
		walker2.setUsername("maliPerica2");

		Walker walker3 = new Walker();
		walker3.setEmail("pero.peric3@gmail.com");
		walker3.setFirstName("Pero3");
		walker3.setLastName("Peric3");
		walker3.setPassword("12345673");
		walker3.setUsername("maliPerica3");
		
		Walker walker4 = new Walker();
		walker4.setEmail("pero.peric4@gmail.com");
		walker4.setFirstName("Pero4");
		walker4.setLastName("Peric4");
		walker4.setPassword("12345674");
		walker4.setUsername("maliPerica4");
		
		Walker walker5 = new Walker();
		walker5.setEmail("pero.peric5@gmail.com");
		walker5.setFirstName("Pero5");
		walker5.setLastName("Peric5");
		walker5.setPassword("123456785");
		walker5.setUsername("maliPerica5");

		

		Walk walk1 = new Walk();
		walk1.setDateTime(Timestamp.valueOf("2020-12-08 03:13:11"));
		walk1.setDuration(10);
		
		Walk walk2 = new Walk();
		walk2.setDateTime(Timestamp.valueOf("2020-12-08 03:13:12"));
		walk2.setDuration(20);
		
		Walk walk3 = new Walk();
		walk3.setDateTime(Timestamp.valueOf("2020-12-08 03:13:13"));
		walk3.setDuration(30);
		
		Walk walk4 = new Walk();
		walk4.setDateTime(Timestamp.valueOf("2017-09-08 03:13:14"));
		walk4.setDuration(40);
		
		Walk walk5 = new Walk();
		walk5.setDateTime(Timestamp.valueOf("2017-09-08 03:13:15"));
		walk5.setDuration(50);
		
		Location location1 = new Location();
		location1.setAddress("Ilica 35");
		location1.setCity("Zagreb");

		Shelter shelter1=new Shelter();
		shelter1.setOIB("11111111111");
		shelter1.setName("prvaudruga");
		shelter1.setUsername("udruga1");
		shelter1.setPassword("12345");
		shelter1.setLocation(location1);
		
		Dog dog1 = new Dog();
		dog1.setDescription("mali");
		dog1.setImage("bijeli");
		dog1.setName("vili");
		dog1.setTypeOfWalk("I");
		dog1.setLocation(location1);
		dog1.setShelter(shelter1);

		Dog dog2 = new Dog();
		dog2.setDescription("mali2");
		dog2.setImage("bijeli2");
		dog2.setName("vili2");
		dog2.setTypeOfWalk("I");
		dog2.setShelter(shelter1);
		
		Dog dog3 = new Dog();
		dog3.setDescription("mali3");
		dog3.setImage("bijeli3");
		dog3.setName("vili3");
		dog3.setTypeOfWalk("I");
		dog3.setShelter(shelter1);
		
		Dog dog4 = new Dog();
		dog4.setDescription("mali4");
		dog4.setImage("bijeli4");
		dog4.setName("vili4");
		dog4.setTypeOfWalk("I");
		dog4.setShelter(shelter1);
		
		Dog dog5 = new Dog();
		dog5.setDescription("mali5");
		dog5.setImage("bijeli5");
		dog5.setName("vili5");
		dog5.setTypeOfWalk("I");
		dog5.setShelter(shelter1);




		shelterService.registerShelter(shelter1);

		walkerService.registerWalker(walker1);
		walkerService.registerWalker(walker2);
		walkerService.registerWalker(walker3);
		walkerService.registerWalker(walker4);
		walkerService.registerWalker(walker5);

				
		dogService.setDog(dog1);
		dogService.setDog(dog2);
		dogService.setDog(dog3);
		dogService.setDog(dog4);
		dogService.setDog(dog5);

		walkService.setWalk(walk1);
		walkService.setWalk(walk2);
		walkService.setWalk(walk3);
		walkService.setWalk(walk4);
		walkService.setWalk(walk5);

		reservationService.createReservation(walker1, walk1, dog1);
		reservationService.createReservation(walker1, walk2, dog2);
		reservationService.createReservation(walker1, walk3, dog4);
		reservationService.createReservation(walker2, walk2, dog3);
		reservationService.createReservation(walker2, walk5, dog5);
		reservationService.createReservation(walker3, walk3, dog3);
		reservationService.createReservation(walker4, walk4, dog4);
		reservationService.createReservation(walker5, walk5, dog5);


	}

}
