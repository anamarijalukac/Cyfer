package cyfer;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import cyfer.service.*;
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
	@Autowired
	private ILocationService locationService;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		
		

		Walker walker1 = new Walker();
		walker1.setEmail("luka.novak@gmail.com");
		walker1.setFirstName("Luka");
		walker1.setLastName("Novak");
		walker1.setPassword("12345671");
		walker1.setUsername("lukaNovak");
		
		Walker walker2 = new Walker();
		walker2.setEmail("nika.babic@gmail.com");
		walker2.setFirstName("Nika");
		walker2.setLastName("Babic");
		walker2.setPassword("12345672");
		walker2.setUsername("nikaBabic");

		Walker walker3 = new Walker();
		walker3.setEmail("lovro.tomic@gmail.com");
		walker3.setFirstName("Lovro");
		walker3.setLastName("Tomic");
		walker3.setPassword("12345673");
		walker3.setUsername("lovroTomic");
		
		Walker walker4 = new Walker();
		walker4.setEmail("lara.novak@gmail.com");
		walker4.setFirstName("Lara");
		walker4.setLastName("Novak");
		walker4.setPassword("12345674");
		walker4.setUsername("laraNovak");
		
		Walker walker5 = new Walker();
		walker5.setEmail("fran.klaric@gmail.com");
		walker5.setFirstName("Fran");
		walker5.setLastName("Klaric");
		walker5.setPassword("123456785");
		walker5.setUsername("franKlaric");

		

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
		//locationService.saveLocation(location1);

		Location location2 = new Location();
		location2.setAddress("Dedovići 9");
		location2.setCity("Zagreb");
		//locationService.saveLocation(location2);

		Shelter shelter1=new Shelter();
		shelter1.setOIB("11111111111");
		shelter1.setName("Snoopy");
		shelter1.setUsername("snoopy");
		shelter1.setPassword("12345");
		shelter1.setLocation(location1);
		shelter1.setImage("https://www.peanuts.com/sites/default/files/sn-color.jpg");

		Shelter shelter2=new Shelter();
		shelter2.setOIB("11111111112");
		shelter2.setName("Mala Šapa");
		shelter2.setUsername("malasapa");
		shelter2.setPassword("123456");
		shelter2.setLocation(location2);
		shelter2.setImage("https://cdn1.vectorstock.com/i/1000x1000/69/50/paw-dog-animal-cute-logo-vector-27106950.jpg");

		
		Dog dog1 = new Dog();
		dog1.setDescription("Dachshund");
		dog1.setImage("https://pngimg.com/uploads/dachshund/dachshund_PNG51.png");
		dog1.setName("Roko");
		dog1.setTypeOfWalk("I");
		dog1.setLocation(location1);
		dog1.setShelter(shelter1);

		Dog dog2 = new Dog();
		dog2.setDescription("Samoyed");
		dog2.setImage("https://upload.wikimedia.org/wikipedia/commons/9/94/My_dog.jpg");
		dog2.setName("Pahuljica");
		dog2.setTypeOfWalk("I");
		dog2.setShelter(shelter1);
		dog2.setLocation(shelter1.getLocation());
		
		Dog dog3 = new Dog();
		dog3.setDescription("Cavalier King Charles Spaniel");
		dog3.setImage("https://upload.wikimedia.org/wikipedia/commons/4/43/Cute_dog.jpg");
		dog3.setName("Dama");
		dog3.setTypeOfWalk("I");
		dog3.setShelter(shelter1);
		dog3.setLocation(shelter1.getLocation());
		
		Dog dog4 = new Dog();
		dog4.setDescription("American Eskimo Dog");
		dog4.setImage("https://upload.wikimedia.org/wikipedia/commons/f/fe/American_Eskimo_Dog_1.jpg");
		dog4.setName("Max");
		dog4.setTypeOfWalk("I");
		dog4.setShelter(shelter1);
		dog4.setLocation(shelter1.getLocation());
		
		Dog dog5 = new Dog();
		dog5.setDescription("Golden Retriever");
		dog5.setImage("https://upload.wikimedia.org/wikipedia/commons/d/dd/Golden_Retriever_Hund_Dog.JPG");
		dog5.setName("Tara");
		dog5.setTypeOfWalk("I");
		dog5.setShelter(shelter1);
		dog5.setLocation(shelter1.getLocation());

		Dog dog6 = new Dog();
		dog6.setDescription("Surfer dog");
		dog6.setImage("https://upload.wikimedia.org/wikipedia/commons/1/1f/SURF_DOGS_USA_DOGS_SURFING.jpg");
		dog6.setName("Loki");
		dog6.setTypeOfWalk("G");
		dog6.setShelter(shelter2);
		dog6.setLocation(shelter2.getLocation());

		Dog dog7 = new Dog();
		dog7.setDescription("French bulldog");
		dog7.setImage("https://upload.wikimedia.org/wikipedia/commons/d/d6/French_bulldog_in_life_jacket.jpg");
		dog7.setName("Dino");
		dog7.setTypeOfWalk("G");
		dog7.setShelter(shelter2);
		dog7.setLocation(shelter2.getLocation());


		shelterService.registerShelter(shelter1);
		shelterService.registerShelter(shelter2);

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
		dogService.setDog(dog6);
		dogService.setDog(dog7);

		walkService.setWalk(walk1);
		walkService.setWalk(walk2);
		walkService.setWalk(walk3);
		walkService.setWalk(walk4);
		walkService.setWalk(walk5);

		reservationService.createReservation(walker1, walk1, dog1);
		reservationService.createReservation(walker1, walk1, dog3);
		reservationService.createReservation(walker1, walk2, dog2);
		reservationService.createReservation(walker1, walk3, dog4);
		reservationService.createReservation(walker2, walk2, dog3);
		reservationService.createReservation(walker2, walk5, dog5);
		reservationService.createReservation(walker3, walk3, dog3);
		reservationService.createReservation(walker4, walk4, dog4);
		reservationService.createReservation(walker5, walk5, dog5);

		//testni primjerak koji ne bi smio raditi!!!
		reservationService.createReservation(walker2, walk5, dog5);

	}

}
