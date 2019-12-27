package com.romanov.validator;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Surgeon;
import com.romanov.model.utils.PersonRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ConsultantValidator extends PersonValidator {

    @Autowired
    private PersonValidator personValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Consultant.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        personValidator.validate(target, errors);

        Consultant consultant = (Consultant) target;

        if(!PersonRole.CONSULTANT.equals(consultant.getPersonRole()))
        {
            errors.reject(
                    ExceptionCode.INVALID_PERSON_ROLE.getCode(),
                    "Invalid consultant object, person role should be CONSULTANT!"
            );
        }


    }

}
