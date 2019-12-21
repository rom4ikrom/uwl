package com.romanov.validator;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.model.utils.Address;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddressValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                Address.ADDRESS_LINE1,
                ExceptionCode.INVALID_FIRST_LINE.getCode(),
                "Invalid address object, first line can't be empty!"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                Address.ADDRESS_LINE2,
                ExceptionCode.INVALID_SECOND_LINE.getCode(),
                "Invalid address object, second line can't be empty!"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                Address.TOWN,
                ExceptionCode.INVALID_TOWN.getCode(),
                "Invalid address object, town can't be empty!"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                Address.POSTCODE,
                ExceptionCode.INVALID_POSTCODE.getCode(),
                "Invalid address object, postcode can't be empty!"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                Address.COUNTRY,
                ExceptionCode.INVALID_COUNTRY.getCode(),
                "Invalid address object, country can't be empty!"
        );

    }
}
