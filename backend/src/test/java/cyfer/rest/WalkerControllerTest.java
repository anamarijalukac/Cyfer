package cyfer.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cyfer.domain.Walker;
import cyfer.service.IReservationService;
import cyfer.service.IWalkerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.Buffer;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WalkerControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void registerWalkerEmailTaken() throws Exception {
        Walker walker1 = new Walker();
        walker1.setUsername("korisnik1");
        walker1.setEmail("korisnik@gmail.com");
        walker1.setFirstName("Kori");
        walker1.setLastName("Snik");
        walker1.setPassword("testiranje1");

        Walker walker2 = new Walker();
        walker2.setUsername("korisnik2");
        walker2.setEmail("korisnik@gmail.com");
        walker2.setFirstName("Kori");
        walker2.setLastName("Snik");
        walker2.setPassword("testiranje2");

        String json1 = new ObjectMapper().writeValueAsString(walker1);
        String json2 = new ObjectMapper().writeValueAsString(walker2);

        this.mockMvc.perform(post("/walker/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/walker/signup").contentType(MediaType.APPLICATION_JSON).content(json2))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void registerWalkerUsernameTaken() throws Exception {
        Walker walker1 = new Walker();
        walker1.setUsername("korisnik");
        walker1.setEmail("korisnik1@gmail.com");
        walker1.setFirstName("Kori");
        walker1.setLastName("Snik");
        walker1.setPassword("testiranje1");

        Walker walker2 = new Walker();
        walker2.setUsername("korisnik");
        walker2.setEmail("korisnik2@gmail.com");
        walker2.setFirstName("Kori");
        walker2.setLastName("Snik");
        walker2.setPassword("testiranje2");

        String json1 = new ObjectMapper().writeValueAsString(walker1);
        String json2 = new ObjectMapper().writeValueAsString(walker2);

        this.mockMvc.perform(post("/walker/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/walker/signup").contentType(MediaType.APPLICATION_JSON).content(json2))
                .andExpect(status().isConflict());
    }

    @Test
    void updateWalker() throws Exception {
        Walker walker1 = new Walker();
        walker1.setUsername("korisnik");
        walker1.setEmail("korisnik1@gmail.com");
        walker1.setFirstName("Kori");
        walker1.setLastName("Snik");
        walker1.setPassword("testiranje1");

        String json1 = new ObjectMapper().writeValueAsString(walker1);

        this.mockMvc.perform(post("/walker/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk());

        walker1 = (Walker) this.mockMvc.perform(post("/walker/login").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk()).andReturn();

        assertEquals(walker1.getUsername(), "korisnik");

        walker1.setUsername("noviKorisnik");
        //bez autentifikacije
        this.mockMvc.perform(post("/walker/update/"+walker1.getWalkerId()).contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isUnauthorized());

        //sa autentifikacijom
        this.mockMvc.perform(post("/walker/update/"+walker1.getWalkerId())
                //.headers("Basic " + Base64.getEncoder().encodeToString(walker1.getPassword().getBytes()))
                .contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isUnauthorized());

    }


}