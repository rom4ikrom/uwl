package com.romanov.model.utils;

import java.util.Arrays;
import java.util.List;

public enum PersonRole {

    PRACTITIONER, SURGEON, CONSULTANT;

    public static List<PersonRole> getValidValues()
    {
        return Arrays.asList(values());
    }

}
