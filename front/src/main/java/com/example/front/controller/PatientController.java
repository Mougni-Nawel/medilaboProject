package com.example.front.controller;

import com.example.front.bean.NoteBean;
import com.example.front.bean.PatientBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Controller
@FeignClient(name="patient", url = "localhost:8080")
public class PatientController {

    @Value("${gateway.url}"+"/patient")
    private String patientUrl;

    @Value("${gateway.url}"+ "/note")
    private String noteUrl;

    private final RestTemplate restTemplate;

    private PatientBean patientBean;

    public PatientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/patients")
    public String getAllPatients(Model model){

        ResponseEntity<List<PatientBean>> responseEntity = restTemplate.exchange(patientUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<PatientBean>>() {});
        List<PatientBean> patientsList = responseEntity.getBody();

        model.addAttribute("patientsList", patientsList);

        return "patient/patients";

    }

    @GetMapping("/patients/{id}")
    public String getAllPatients(Model model, @PathVariable int id){

        ResponseEntity<PatientBean> responseEntity = restTemplate.exchange(patientUrl+"/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<PatientBean>() {});
        PatientBean patient = responseEntity.getBody();

        ResponseEntity<List<NoteBean>> responseEntityNote = restTemplate.exchange(noteUrl+"/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<List<NoteBean>>() {});
        List<NoteBean> noteList = responseEntityNote.getBody();

        model.addAttribute("notesList", noteList);
        System.out.println("CONSOLE ; "+noteList);

        model.addAttribute("patient", patient);

        return "patient/patient";

    }

    @GetMapping("/patients/update/{id}")
    public String getUpdatePatientPage(Model model, @PathVariable int id){

        ResponseEntity<PatientBean> responseEntity = restTemplate.exchange(patientUrl+"/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<PatientBean>() {});
        PatientBean patient = responseEntity.getBody();
        System.out.println("Patient : "+patient.getFirstname());
        model.addAttribute("patientData", patient);
        model.addAttribute("updatePatient", new PatientBean());

        return "patient/update";

    }


    @PostMapping("/patients/update/{id}")
    public String updatePatient(Model model, @PathVariable int id, PatientBean patient){

        ResponseEntity<PatientBean> responseEntity = restTemplate.postForEntity(patientUrl+"/"+id+"/update", patient, PatientBean.class);

        return "redirect:/patients";

    }


    @GetMapping("/patients/add")
    public String getUpdatePatientPage(Model model){

        model.addAttribute("newPatient", new PatientBean());

        return "patient/add";

    }

    @PostMapping("/patients/add")
    public String addPatient(Model model, PatientBean patient){

        ResponseEntity<PatientBean> responseEntity = restTemplate.postForEntity(patientUrl+"/add", patient, PatientBean.class);

        return "redirect:/patients";

    }

}
