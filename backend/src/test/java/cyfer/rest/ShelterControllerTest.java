package cyfer.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cyfer.domain.Location;
import cyfer.domain.Reservation;
import cyfer.domain.Shelter;
import cyfer.domain.Walker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Base64Utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShelterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shelterRegistrationOIBTaken() throws Exception {
        //stvara i registrira prvi shelter
        Location location1 = new Location();
        location1.setAddress("Ilica 37");
        location1.setCity("Zagreb");

        Shelter shelter1=new Shelter();
        shelter1.setOIB("22000000000");
        shelter1.setName("Shelter2");
        shelter1.setUsername("shelter2");
        shelter1.setPassword("12345");
        shelter1.setLocation(location1);
        shelter1.setImage("https://www.peanuts.com/sites/default/files/sn-color.jpg");

        String json1 = new ObjectMapper().writeValueAsString(shelter1);

        this.mockMvc.perform(post("/shelter/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk());

        //stvara i registriraj drugi shelter
        Shelter shelter2=new Shelter();
        //OIB isti kao u gornjem shelteru - NOT ACCEPTABLE
        shelter2.setOIB(shelter1.getOIB());
        shelter2.setName("Shelter3");
        shelter2.setUsername("shelter3");
        shelter2.setPassword("12345");
        shelter2.setLocation(location1);
        shelter2.setImage("https://www.peanuts.com/sites/default/files/sn-color.jpg");

        json1 = new ObjectMapper().writeValueAsString(shelter2);
        this.mockMvc.perform(post("/shelter/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isNotAcceptable());

        //popravi OIB - OK
        shelter2.setOIB("22000000001");
        json1 = new ObjectMapper().writeValueAsString(shelter2);

        this.mockMvc.perform(post("/shelter/signup").contentType(MediaType.APPLICATION_JSON).content(json1)).
                andExpect(status().isOk());
    }



    @Test
    void shelterRegistrationUsernameTaken() throws Exception {
        //stvara i registrira shelter
        Location location1 = new Location();
        location1.setAddress("Ilica 38");
        location1.setCity("Zagreb");

        Shelter shelter1=new Shelter();
        shelter1.setOIB("12345678989");
        shelter1.setName("Ella");
        shelter1.setUsername("ella");
        shelter1.setPassword("12345");
        shelter1.setLocation(location1);
        shelter1.setImage("https://www.peanuts.com/sites/default/files/sn-color.jpg");

        String json1 = new ObjectMapper().writeValueAsString(shelter1);
        this.mockMvc.perform(post("/shelter/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk());

        //stvori i registriraj drugi shelter s istim usernamemom kao prvi - CONFLICT
        Shelter shelter2 = new Shelter();
        shelter2.setOIB("12344444447");
        shelter2.setName("Ella");
        shelter2.setUsername("ella");
        shelter2.setPassword("12345");
        shelter2.setLocation(location1);
        shelter2.setImage("https://www.peanuts.com/sites/default/files/sn-color.jpg");

        json1 = new ObjectMapper().writeValueAsString(shelter2);
        this.mockMvc.perform(post("/shelter/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isConflict());

        //postavi drugačiji username - OK
        shelter2.setUsername("lily");
        json1 = new ObjectMapper().writeValueAsString(shelter2);

        this.mockMvc.perform(post("/shelter/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk());

    }

    @Test
    void updateShelterInfo() throws Exception {

        //stvori i registriraj shelter
        Location location1 = new Location();
        location1.setAddress("Ilica 35");
        location1.setCity("Zagreb");

        Shelter shelter1=new Shelter();
        shelter1.setOIB("11111110000");
        shelter1.setName("Shelter1");
        shelter1.setUsername("shelter1");
        shelter1.setPassword("12345");
        shelter1.setLocation(location1);
        shelter1.setImage("https://www.peanuts.com/sites/default/files/sn-color.jpg");

        String json1 = new ObjectMapper().writeValueAsString(shelter1);
        String izvornaLozinka = shelter1.getPassword();

        MvcResult result = this.mockMvc.perform(post("/shelter/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        shelter1 = new ObjectMapper().readValue(content, Shelter.class);

        //promjena imena
        shelter1.setName("NovoIme");
        json1 = new ObjectMapper().writeValueAsString(shelter1);

        //bez autentifikacije - unauthorized
        this.mockMvc.perform(post("/shelter/update/"+shelter1.getShelterId()).contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isUnauthorized());

        //autentifikacija
        String auth = Base64Utils.encodeToString((shelter1.getUsername()+":"+izvornaLozinka).getBytes());
        //dohvaćanje info
        result = this.mockMvc.perform(post("/shelter/update/"+shelter1.getShelterId()).contentType(MediaType.APPLICATION_JSON).content(json1)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + auth))
                .andExpect(status().isOk()).andReturn();

        content = result.getResponse().getContentAsString();
        shelter1 = new ObjectMapper().readValue(content, Shelter.class);
        assertEquals(shelter1.getName(), "NovoIme");
    }



    @Test
    void deleteShelter() throws Exception {

        //stvori i registriraj shelter
        Location location1 = new Location();
        location1.setAddress("Ilica 39");
        location1.setCity("Zagreb");

        Shelter shelter1=new Shelter();
        shelter1.setOIB("55566677788");
        shelter1.setName("Dita");
        shelter1.setUsername("dita");
        shelter1.setPassword("12345");
        shelter1.setLocation(location1);
        shelter1.setImage("https://www.peanuts.com/sites/default/files/sn-color.jpg");

        String json1 = new ObjectMapper().writeValueAsString(shelter1);
        String izvornaLozinka = shelter1.getPassword();

        MvcResult result = this.mockMvc.perform(post("/shelter/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        shelter1 = new ObjectMapper().readValue(content, Shelter.class);

        //brisanje bez autentifikacije - unauthorized
        this.mockMvc.perform(post("/shelter/delete/"+shelter1.getShelterId()).contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isUnauthorized());

        //autentifikacija
        String auth = Base64Utils.encodeToString((shelter1.getUsername()+":"+izvornaLozinka).getBytes());

        //brisanje sa auntentifikacijom - OK
        this.mockMvc.perform(post("/shelter/delete/"+shelter1.getShelterId()).contentType(MediaType.APPLICATION_JSON).content(json1)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + auth))
                .andExpect(status().isOk());

    }

}