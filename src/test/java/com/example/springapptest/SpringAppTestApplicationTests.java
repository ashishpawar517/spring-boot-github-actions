package com.example.springapptest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class SpringAppTestApplicationTests {

    @Test
    public void getGreetingStatusCode() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + "/greet", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getGreetingMessage() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + "/greet", String.class);
        assertThat(response.getBody(), equalTo("hello world"));
    }

}
