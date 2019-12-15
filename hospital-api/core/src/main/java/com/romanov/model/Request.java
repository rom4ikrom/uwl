package com.romanov.model;

import java.util.Date;

public class Request {

    private String id;
    private Date date;
    private RequestType requestType;

    private Request() {};

    enum RequestType {

        APPOINTMENT, ANALYSIS, TREATMENT, HISTORY;

    }

}
