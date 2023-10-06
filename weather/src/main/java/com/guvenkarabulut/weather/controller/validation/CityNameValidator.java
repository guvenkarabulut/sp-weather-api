package com.guvenkarabulut.weather.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;


@Component
public class CityNameValidator implements ConstraintValidator<CityNameConstraint, String> {
    @Override
    public void initialize(CityNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        s=s.replaceAll("[^a-zA-Z0-9]","");
        return !StringUtils.isNumeric(s) && !StringUtils.isAllBlank(s);

    }
}
