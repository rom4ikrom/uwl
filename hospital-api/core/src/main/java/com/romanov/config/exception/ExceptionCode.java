package com.romanov.config.exception;

public enum ExceptionCode {

    //GENERAL
    UNKNOWN, MISSING_PARAM, INVALID_FIELD,

    //NOT FOUND
    PATIENT_NOT_FOUND, HISTORY_NOT_FOUND, REQUEST_NOT_FOUND, PRACTITIONER_NOT_FOUND, SURGEON_NOT_FOUND, CONSULTANT_NOT_FOUND,

    //PERSON
    INVALID_FIRST_NAME, INVALID_LAST_NAME, INVALID_AGE, INVALID_EMAIL, INVALID_PHONE, INVALID_PERSON_ROLE, INVALID_ADDRESS,

    //ADDRESS
    INVALID_FIRST_LINE, INVALID_SECOND_LINE, INVALID_POSTCODE, INVALID_TOWN, INVALID_COUNTRY,

    //TREATMENT
    INVALID_SURGEON_NUMBER, INVALID_CONSULTANT_NUMBER, INVALID_MEDICINE_NUMBER,

    //REQUEST
    INVALID_REQUEST;

    public static ExceptionCode getByCode(String code) {

        if(code == null) return ExceptionCode.UNKNOWN;

        for(ExceptionCode thortfulExceptionCode : values()) {
            if(code.equals(thortfulExceptionCode.getCode())) {
                return thortfulExceptionCode;
            }
        }

        return ExceptionCode.UNKNOWN;
    }

    public String getCode() {
        return this.toString();
    }

}
