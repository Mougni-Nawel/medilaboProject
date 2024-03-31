package com.example.reportRisk.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;

@Service
public class NoteProxyService {

    private final RestTemplate restTemplate;

    public NoteProxyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Collection<String> fetchSymptomsFromNote(int id) {
        String noteUrl = "http://localhost:8080";
        String endpoint = "/note/" + id + "/symptoms";
        String url = noteUrl + endpoint;

        try {
            ResponseEntity<Collection> response = restTemplate.getForEntity(url, Collection.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                // Log the unexpected response status code
                System.err.println("Unexpected status code: " + response.getStatusCode());
                return Collections.emptyList();
            }
        } catch (HttpClientErrorException e) {
            // Log the exception and return an empty collection
            System.err.println("Error while fetching symptoms: " + e.getStatusCode() + " - " + e.getStatusText());
            return Collections.emptyList();
        } catch (Exception e) {
            // Log any other unexpected exceptions
            System.err.println("Unexpected error: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
