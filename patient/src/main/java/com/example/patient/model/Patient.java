package com.example.patient.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;
    private String lastname;
    private Date birthdate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;
    private String phone;

    public Patient(int id, String firstname, String lastname, String birthdate, Gender gender, String address, String phone) throws ParseException {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public Patient() {
    }
}
