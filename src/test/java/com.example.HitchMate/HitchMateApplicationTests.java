package com.example.HitchMate;

import com.example.HitchMate.Entity.Marker;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HitchMateApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAMarkerDataWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1","abc123").getForEntity("/markers/99",String.class);
        System.out.println(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody());
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        System.out.println(documentContext.toString());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(22);
        Number lat = documentContext.read("$.lat");
        assertThat(lat).isEqualTo(22);
        Number lng = documentContext.read("$.lng");
        assertThat(lng).isEqualTo(22);
        String description = documentContext.read("$.description");
        assertThat(description).isEqualTo("test");
        Number userid = documentContext.read("$.userid");
        assertThat(userid).isEqualTo(22);
    }

    @Test
    void shouldReturnAllMarkers() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1","abc123").getForEntity("/markers",String.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DirtiesContext
    void shouldCreateAMarkerWhenDataIsSend() {
        Marker newMarker = new Marker((Long) null, 12.00,12.00,"test","sarah1");
        ResponseEntity<Void> createResponse = restTemplate.withBasicAuth("sarah1","abc123").postForEntity("/markers", newMarker, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewMarker = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .getForEntity(locationOfNewMarker, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        Double lat = documentContext.read("$.lat");
        Double lng = documentContext.read("$.lng");

        assertThat(id).isNotNull();
        assertThat(lat).isEqualTo(250.00);
    }



}