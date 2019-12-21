package com.romanov.model.staff;

import java.util.Arrays;
import java.util.List;

public enum PersonRole {

    PATIENT, PRACTITIONER, SURGEON, CONSULTANT;

    public static List<PersonRole> getValidValues()
    {
        return Arrays.asList(values());
    }

}
