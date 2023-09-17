package com.example.HitchMate;

import com.example.HitchMate.Entity.Marker;

import com.example.HitchMate.Entity.User;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class HitchMateApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    void shouldReturnAMarkerDataWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1","abc123").getForEntity("/markers/2",String.class);
        System.out.println(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody());
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        System.out.println(documentContext.toString());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(2);
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
        ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1","abc123").getForEntity("/markers",String.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DirtiesContext
    void shouldCreateAMarkerWhenDataIsSend() {
        List<Marker> markers = new ArrayList<>();
        Marker newMarker = new Marker();
        newMarker.setId(55L);
        newMarker.setTitle("Las Palmas");
        newMarker.setLat(12.00);
        newMarker.setLng(12.00);
        newMarker.setInfo("test");
        markers.add(newMarker);
        newMarker.setUser(new User(666L, "test1", "test1", "asd@asd.com", markers, null));
        ResponseEntity<Void> createResponse = restTemplate.withBasicAuth("sarah1","abc123").postForEntity("/markers", newMarker, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewMarker = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .getForEntity(locationOfNewMarker, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String title = documentContext.read("$.title");
        Double lat = documentContext.read("$.lat");
        Double lng = documentContext.read("$.lng");
        String info = documentContext.read("$.info");
        User user = documentContext.read("$.user_id");


        assertThat(id).isNotNull();
        assertThat(lat).isEqualTo(250.00);
    }



}