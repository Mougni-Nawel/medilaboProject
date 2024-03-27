package com.example.reportRisk.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteBean {

    private String id;
    private int patId;
    private String patient;
    private String note;


}
