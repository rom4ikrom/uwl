package com.romanov.model;

import lombok.Data;

import java.util.List;

@Data
public class Medicine {

    private String id;

    private String name;

    private List<String> contraindications;
    private List<String> indications;
    private List<String> composition;

    private Double price;
}
