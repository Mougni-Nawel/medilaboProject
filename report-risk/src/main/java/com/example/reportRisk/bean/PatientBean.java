package com.example.reportRisk.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PatientBean {

    private int id;
    private String firstname;
    private String lastname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    private String gender;
    private String address;
    private String phone;

}
