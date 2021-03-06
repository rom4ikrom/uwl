package com.romanov.validator;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.model.utils.Address;
import com.romanov.model.utils.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    @Autowired
    private AddressValidator addressValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                Person.FIRST_NAME,
                ExceptionCode.INVALID_FIRST_NAME.getCode(),
                "Invalid person object, first name can't be empty!"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                Person.LAST_NAME,
                ExceptionCode.INVALID_FIRST_NAME.getCode(),
                "Invalid person object, first name can't be empty!"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                Person.AGE,
                ExceptionCode.INVALID_FIRST_NAME.getCode(),
                "Invalid person object, first name can't be empty!"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                Person.EMAIL,
                ExceptionCode.INVALID_FIRST_NAME.getCode(),
                "Invalid person object, first name can't be empty!"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                Person.PHONE,
                ExceptionCode.INVALID_FIRST_NAME.getCode(),
                "Invalid person object, first name can't be empty!"
        );

//        ValidationUtils.rejectIfEmptyOrWhitespace(
//                errors,
//                Person.PERSON_ROLE,
//                ExceptionCode.INVALID_PERSON_ROLE.getCode(),
//                "Invalid person object, person role can't be empty!"
//        );

        Person person = (Person) target;

        if(person.getAge() < 16)
        {
            errors.reject(
                    ExceptionCode.INVALID_AGE.getCode(),
                    "Invalid person object, age can't be less than 16!"
            );
        }

        if(person.getPhone() != null && (person.getPhone().length() < 10 || person.getPhone().length() > 12))
        {
            errors.reject(
                    ExceptionCode.INVALID_PHONE.getCode(),
                    "Invalid person object, phone number should be between 10 and 12 numbers!"
            );
        }

        if(person.getAddresses() == null || person.getAddresses().isEmpty())
        {
            errors.reject(
                    ExceptionCode.INVALID_ADDRESS.getCode(),
                    "Invalid person object, address can't be empty!"
            );
        } else {

            int index = 0;
            for(Address address : person.getAddresses())
            {
                errors.pushNestedPath("addresses[" + index + "]");
                ValidationUtils.invokeValidator(addressValidator, address, errors);
                errors.popNestedPath();
                index++;
            }
        }
    }
}
