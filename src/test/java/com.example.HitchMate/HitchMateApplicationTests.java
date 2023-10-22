package com.example.HitchMate;

import com.example.HitchMate.Entity.Marker;
import com.example.HitchMate.Entity.Role;
import com.example.HitchMate.Entity.User;
import com.example.HitchMate.repositories.MarkerRepository;
import com.example.HitchMate.repositories.UserRepository;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class HitchMateApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MarkerRepository markerRepository;
    @Test
    void shouldReturnAMarkerDataWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1","abc123").getForEntity("/markers/1",String.class);
        String responseBody = response.getBody();
        System.out.println("Response Body: " + responseBody);
        DocumentContext documentContext = JsonPath.parse(responseBody);
        System.out.println("Extracted marker_id: " + documentContext.read("$.marker_id"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Number id = documentContext.read("$.marker_id");
        assertThat(id).isEqualTo(1);
        String title = documentContext.read("$.title");
        assertThat(title).isEqualTo("title");
        Number lat = documentContext.read("$.lat");
        assertThat(lat).isEqualTo(12.0);
        Number lng = documentContext.read("$.lng");
        assertThat(lng).isEqualTo(12.0);
        String description = documentContext.read("$.info");
        assertThat(description).isEqualTo("test");

    }

    @Test
    @DirtiesContext
    void shouldReturnAllMarkers() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("sarah2","abc1234").getForEntity("/markers",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DirtiesContext
    void shouldCreateAMarkerWhenDataIsSend() {
        List<Marker> markers = new ArrayList<>();

        // Create a user without roles initially
        User newUser = new User(3L, "hank1", "abc12345", "test@test.pl", null);
        userRepository.save(newUser);
        // Register the user without roles
        User createdUser = userRepository.findByUsername("hank1");
        createdUser.getMarkers().size();
        System.out.println("Created User: " + createdUser.getId());
        System.out.println("Password: " + createdUser.getPassword());

        Role adminRole = new Role(null, "ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        createdUser.setRoles(roles);
        userRepository.save(createdUser);
        System.out.println("userRepository: " + userRepository.findAll());

        ResponseEntity<Void> createUserResponse = restTemplate.withBasicAuth("hank1", "abc12345").postForEntity("/users", createdUser, Void.class);


        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);



//
        // Now create the marker associated with the user
//
//        // Create the marker
//        assertThat(createMarkerResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//
//        URI locationOfNewMarker = createMarkerResponse.getHeaders().getLocation();
//        ResponseEntity<String> getResponse = restTemplate
//                .withBasicAuth("hank", "abc123")
//                .getForEntity(locationOfNewMarker, String.class);
//        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
//        Number id = documentContext.read("$.id");
//        String title = documentContext.read("$.title");
//        Double lat = documentContext.read("$.lat");
//        Double lng = documentContext.read("$.lng");
//        String info = documentContext.read("$.info");
//        User user = documentContext.read("$.user");
//
//        assertThat(id).isNotNull();
//        assertThat(lat).isEqualTo(4.0); // Update the expected value here
    }




}