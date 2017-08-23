package ru.mail.denis.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mail.denis.service.DTOmodels.BookDTO;
import ru.mail.denis.service.DTOmodels.UserDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 22.08.2017.
 */

@Component
public class BookValidator implements Validator {

    private Pattern patternName;
    private Pattern patternAuthor;
    private Pattern patternDescription;
    private Matcher matcherName;
    private Matcher matcherAuthor;
    private Matcher matcerDescription;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(BookDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BookDTO bookDTO=(BookDTO)o;
        ValidationUtils.rejectIfEmpty(errors, "bookName", "error.bookName.empty");
        ValidationUtils.rejectIfEmpty(errors, "bookAuthor", "error.bookAuthor.empty");
        ValidationUtils.rejectIfEmpty(errors, "bookQuantity", "error.bookQuantity.empty");
        ValidationUtils.rejectIfEmpty(errors, "bookPrice", "error.bookPrice.empty");
        ValidationUtils.rejectIfEmpty(errors, "bookDescription", "error.bookDescription.empty");

        String NAME_PATTERN="[a-zA-Z]+";
        String AUTHOR_PATTERN="[a-zA-Z]+";
        String DESCRIPTION_PATTERN="[a-zA-Z]+";

        patternName=Pattern.compile(NAME_PATTERN);
        matcherName=patternName.matcher(bookDTO.getBookName());
        if (!matcherName.matches()) {
            errors.rejectValue("bookName", "error.bookName.regex");
        }
        patternAuthor=Pattern.compile(AUTHOR_PATTERN);
        matcherAuthor=patternAuthor.matcher(bookDTO.getBookAuthor());
        if (!matcherAuthor.matches()) {
            errors.rejectValue("bookAuthor", "error.bookAuthor.regex");
        }
        if (bookDTO.getBookQuantity()!=null){
            try{
                Integer bookQuantity= bookDTO.getBookQuantity();
            }catch (NumberFormatException e){
                errors.rejectValue("bookQuantity", "error.bookQuantity.regex");
            }
        }
        if (bookDTO.getBookPrice()!=null){
            try{
                Double bookPrice= bookDTO.getBookPrice();
            }catch (NumberFormatException e){
                errors.rejectValue("bookPrice", "error.bookPrice.regex");
            }
        }

        patternDescription=Pattern.compile(DESCRIPTION_PATTERN);
        matcerDescription=patternDescription.matcher(bookDTO.getBookDescription());
        if (!matcerDescription.matches()) {
            errors.rejectValue("bookDescription", "error.bookDescription.regex");
        }
    }
}