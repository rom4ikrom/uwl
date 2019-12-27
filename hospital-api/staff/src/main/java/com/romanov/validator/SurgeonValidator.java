package com.romanov.validator;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.staff.Surgeon;
import com.romanov.model.utils.PersonRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class SurgeonValidator extends PersonValidator {

    @Autowired
    private PersonValidator personValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Surgeon.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        personValidator.validate(target, errors);

        Surgeon surgeon = (Surgeon) target;

        if(!PersonRole.SURGEON.equals(surgeon.getPersonRole()))
        {
            errors.reject(
                    ExceptionCode.INVALID_PERSON_ROLE.getCode(),
                    "Invalid surgeon object, person role should be SURGEON!"
            );
        }


    }

}
