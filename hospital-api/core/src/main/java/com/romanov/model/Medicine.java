package com.romanov.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
//@Entity
public class Medicine {

    private String id;

    private String name;

    private List<String> contraindications;
    private List<String> indications;
    private List<String> composition;

    private Double price;

    private Medicine() {}
}
