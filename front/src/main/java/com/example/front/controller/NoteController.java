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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@FeignClient(name= "note", url = "localhost:8080")
public class NoteController {

    @Value("${gateway.url}"+ "/note")
    private String noteUrl;

    private final RestTemplate restTemplate;

    private NoteBean noteBean;

    public NoteController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //@GetMapping("/patients/{id}")
    public String getAllNotes(Model model, @PathVariable int id){

        ResponseEntity<List<NoteBean>> responseEntity = restTemplate.exchange(noteUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<NoteBean>>() {});
        List<NoteBean> noteList = responseEntity.getBody();

        model.addAttribute("notesList", noteList);
        System.out.println("CONSOLE ; "+noteList);

        return "patient/patient";

    }

    @PostMapping("/note/add")
    public String updatePatient(Model model, NoteBean newNote){

        ResponseEntity<NoteBean> responseEntity = restTemplate.postForEntity(noteUrl+"/add", newNote, NoteBean.class);

        return "redirect:/patients";

    }
}
