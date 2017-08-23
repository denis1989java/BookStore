package ru.mail.denis.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 15.08.2017.
 */

@ValidEmail
public class EmailValidator implements ConstraintValidator<ValidEmail,String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN="^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"+"[_A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public void initialize(ValidEmail validEmail) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return (validateEmail(email));
    }

    private boolean validateEmail(String email){
        pattern=Pattern.compile(EMAIL_PATTERN);
        matcher=pattern.matcher(email);
        return matcher.matches();
    }
}