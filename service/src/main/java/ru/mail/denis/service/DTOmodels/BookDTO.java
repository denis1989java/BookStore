package ru.mail.denis.service.DTOmodels;

import ru.mail.denis.models.Changable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by user on 27.07.2017.
 */
public class BookDTO implements Serializable {

    private static final long serialVersionUID = 3034640308530860285L;
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private Integer bookQuantity;
    private Double bookPrice;
    private String bookDescription;
    private Changable changable;

    public Changable getChangable() {
        return changable;
    }

    public void setChangable(Changable changable) {
        this.changable = changable;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}