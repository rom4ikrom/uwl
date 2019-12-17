package com.romanov.model;

import lombok.Data;

import java.util.Date;

@Data
public class Request {

    private String id;
    private Date date;
    private RequestType requestType;

    private Request() {};

    enum RequestType {

        APPOINTMENT, ANALYSIS, TREATMENT, HISTORY;

    }

}
