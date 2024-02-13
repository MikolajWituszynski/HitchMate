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
import org.springframework.test.context.junit4.SpringRunner;

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




}