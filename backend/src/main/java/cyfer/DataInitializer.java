package cyfer;

import java.sql.Timestamp;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cyfer.domain.Dog;
import cyfer.domain.Walk;
import cyfer.domain.Walker;
import cyfer.service.IDogService;
import cyfer.service.IReservationService;
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

	

	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		
		Walker walker1=new Walker();
		walker1.setEmail("pero.peric@gmail.com");
		walker1.setFirstName("Pero");
		walker1.setLastName("Peric");
		walker1.setPassword("12345678");
		walker1.setUsername("maliPerica");
		
		Walk walk1=new Walk();
		walk1.setDateTime(Timestamp.valueOf("2017-09-08 03:13:10"));
		walk1.setDuration(50);
		
		Dog dog1=new Dog();
		dog1.setDescription("mali");
		dog1.setImage("bijeli");
		dog1.setName("vili");
		dog1.setTypeOfWalk("I");
		
		
		walkerService.registerWalker(walker1);
		
		dogService.setDog(dog1);
		
		walkService.setWalk(walk1);
		
		
		
	}

	

}
