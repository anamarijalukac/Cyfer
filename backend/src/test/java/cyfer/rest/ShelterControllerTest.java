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
    void updateShelterInfo() throws Exception {

        //stvori i registriraj shelter
        Location location1 = new Location();
        location1.setAddress("Ilica 35");
        location1.setCity("Zagreb");

        Shelter shelter1=new Shelter();
        shelter1.setOIB("11111110000");
        shelter1.setName("Shelter123");
        shelter1.setUsername("shelter123");
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
        this.mockMvc.perform(post("/shelter/update/"+shelter1.getShelterId()).content(json1))
                .andExpect(status().isUnauthorized());

        //autentifikacija
        String auth = Base64Utils.encodeToString((shelter1.getUsername()+":"+izvornaLozinka).getBytes());
        //dohvaćanje info
        result = this.mockMvc.perform(post("/shelter/info/"+shelter1.getShelterId()).content(json1)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + auth))
                .andExpect(status().isOk()).andReturn();

        content = result.getResponse().getContentAsString();
        shelter1 = new ObjectMapper().readValue(content, Shelter.class);
        assertEquals(shelter1.getName(), "NovoIme");
    }

    @Test
    void shelterRegistration() {

    }

    @Test
    void updateShejlterInfo() throws Exception {

        //stvori i registriraj shelter
        Location location1 = new Location();
        location1.setAddress("Ilica 35");
        location1.setCity("Zagreb");

        Shelter shelter1=new Shelter();
        shelter1.setOIB("11111110000");
        shelter1.setName("Shelter123");
        shelter1.setUsername("shelter123");
        shelter1.setPassword("12345");
        shelter1.setLocation(location1);
        shelter1.setImage("https://www.peanuts.com/sites/default/files/sn-color.jpg");

        String json1 = new ObjectMapper().writeValueAsString(shelter1);
        String izvornaLozinka = shelter1.getPassword();

        MvcResult result = this.mockMvc.perform(post("/shelter/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        shelter1 = new ObjectMapper().readValue(content, Shelter.class);

        //autentifikacija
        String auth = Base64Utils.encodeToString((shelter1.getUsername()+":"+izvornaLozinka).getBytes());
        result = this.mockMvc.perform(get("/shelter/info/"+shelter1.getShelterId())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
                .contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk()).andReturn();

    }

}