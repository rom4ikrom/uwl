package com.romanov.validator;

import com.romanov.model.client.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class PatientValidator extends PersonValidator {

    @Autowired
    private PersonValidator personValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Patient.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        personValidator.validate(target, errors);

//        Patient patient = (Patient) target;
//
//        if(!PersonRole.PATIENT.equals(patient.getPersonRole()))
//        {
//            errors.reject(
//                    ExceptionCode.INVALID_PERSON_ROLE.getCode(),
//                    "Invalid patient object, person role should be PATIENT!"
//            );
//        }


    }

}
