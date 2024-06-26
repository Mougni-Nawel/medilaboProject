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
@FeignClient(name = "note", url = "http://gateway-container:8080/note")
public class NoteController {

    @Value("${gateway.url}"+ "/note")
    private String noteUrl;

    private final RestTemplate restTemplate;

    private NoteBean noteBean;

    public NoteController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<NoteBean> getAllNotes(Model model, @PathVariable int id){

        ResponseEntity<List<NoteBean>> responseEntity = restTemplate.exchange(noteUrl+"/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<List<NoteBean>>() {});
        List<NoteBean> noteList = responseEntity.getBody();

        return noteList;

    }

    @PostMapping("/note/add")
    public String updatePatient(Model model, NoteBean newNote){

        ResponseEntity<NoteBean> responseEntity = restTemplate.postForEntity(noteUrl+"/add", newNote, NoteBean.class);

        return "redirect:/patients";

    }
}
