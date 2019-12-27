package com.romanov.validator;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.model.client.Patient;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.utils.PersonRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class PractitionerValidator extends PersonValidator {

    @Autowired
    private PersonValidator personValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Practitioner.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        personValidator.validate(target, errors);

        Practitioner practitioner = (Practitioner) target;

        if(!PersonRole.PRACTITIONER.equals(practitioner.getPersonRole()))
        {
            errors.reject(
                    ExceptionCode.INVALID_PERSON_ROLE.getCode(),
                    "Invalid practitioner object, person role should be PRACTITIONER!"
            );
        }


    }

}
