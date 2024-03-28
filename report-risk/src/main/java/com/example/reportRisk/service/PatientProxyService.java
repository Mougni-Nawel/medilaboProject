package com.example.reportRisk.service;

import com.example.reportRisk.bean.PatientBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PatientProxyService {

    private final RestTemplate restTemplate;

    public PatientProxyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<PatientBean> fetchPatientFromId(int id) {

        String noteUrl = "http://localhost:8080";
        String endpoint = "/patient/"+id;

        String url = noteUrl + endpoint;

        ResponseEntity<PatientBean> response = restTemplate.getForEntity(url, PatientBean.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }

    }

}
