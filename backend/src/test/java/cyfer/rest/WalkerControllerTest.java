package cyfer.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cyfer.domain.Walk;
import cyfer.domain.Walker;
import cyfer.service.IReservationService;
import cyfer.service.IWalkerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Base64Utils;

import java.nio.Buffer;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class WalkerControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void registerWalkerEmailTaken() throws Exception {

        //pokušaj registriranja dva korisnika s istim usernameom - not acceptable

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

        //pokušaj registriranja dva korisnika s istim usernameom - confllict

        Walker walker1 = new Walker();
        walker1.setUsername("korisnik3");
        walker1.setEmail("korisnik3@gmail.com");
        walker1.setFirstName("Kori");
        walker1.setLastName("Snik");
        walker1.setPassword("testiranje1");

        Walker walker2 = new Walker();
        walker2.setUsername("korisnik3");
        walker2.setEmail("korisnik33@gmail.com");
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
    void updateWalkerWithandWithoutAuthentification() throws Exception {
        Walker walker1 = new Walker();
        walker1.setUsername("korisnik4");
        walker1.setEmail("korisnik4@gmail.com");
        walker1.setFirstName("Kori");
        walker1.setLastName("Snik");
        walker1.setPassword("testiranje1");
        String izvorniPassword = walker1.getPassword();

        String json1 = new ObjectMapper().writeValueAsString(walker1);

        MvcResult result = this.mockMvc.perform(post("/walker/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        walker1 = new ObjectMapper().readValue(content, Walker.class);

        assertEquals(walker1.getUsername(), "korisnik4");

        //promijeni username
        String stariUsername = walker1.getUsername();
        walker1.setUsername("noviKorisnik");
        json1 = new ObjectMapper().writeValueAsString(walker1);

        //bez autentifikacije - unauthorized
        this.mockMvc.perform(post("/walker/update/" + walker1.getWalkerId()).contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isUnauthorized());

        //sa autentifikacijom - ok
        String auth = Base64Utils.encodeToString((stariUsername + ":" + izvorniPassword).getBytes());
        result = this.mockMvc.perform(post("/walker/update/" + walker1.getWalkerId())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
                .contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk()).andReturn();

        content = result.getResponse().getContentAsString();
        walker1 = new ObjectMapper().readValue(content, Walker.class);

        assertEquals(walker1.getUsername(), "noviKorisnik");

    }

    @Test
    void createReservationAuthorization() throws Exception {

        //registracija korisnika
        Walker walker1 = new Walker();
        walker1.setUsername("korisnik5");
        walker1.setEmail("korisnik5@gmail.com");
        walker1.setFirstName("Kori");
        walker1.setLastName("Snik");
        walker1.setPassword("testiranje1");
        String izvorniPassword = walker1.getPassword();

        String json1 = new ObjectMapper().writeValueAsString(walker1);

        MvcResult result = this.mockMvc.perform(post("/walker/signup").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        walker1 = new ObjectMapper().readValue(content, Walker.class);

        assertEquals(walker1.getUsername(), "korisnik5");

        //stvaranje šetnje
        Walk walk = new Walk();
        walk.setDateTime(new Timestamp(new Date().getTime()));
        walk.setDuration(5);

        json1 = new ObjectMapper().writeValueAsString(walk);

        //bez autentifikacije - unauthorized
        this.mockMvc.perform(post("/reserve/13").contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isUnauthorized());

        //sa autentifikacijom - ok
        String auth = Base64Utils.encodeToString((walker1.getUsername() + ":" + izvorniPassword).getBytes());
        result = this.mockMvc.perform(post("/reserve/13")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + auth)
                .contentType(MediaType.APPLICATION_JSON).content(json1))
                .andExpect(status().isOk()).andReturn();

    }


}