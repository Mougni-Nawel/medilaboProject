package com.example.reportRisk.controller;

import com.example.reportRisk.ReportRiskApplication;
import com.example.reportRisk.service.ReportRiskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diagnostic")
@Slf4j
public class ReportRiskController {

    @Autowired
    ReportRiskService service;

    @GetMapping("/reportRisk/{id}")
    public String getReportRisk(@PathVariable("id") int id) {

        return service.determineRisks(id);

    }

}
