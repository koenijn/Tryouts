/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.beanvalidation.model;

import java.util.Date;
import javax.validation.constraints.*;
import nl.vandenboomen.tryouts.beanvalidation.type.Gender;

/**
 *
 * @author mark
 */
public class Person {
    @NotNull(message="No firstname specified!")
    @Size(max=30,message="Firstname is to long")
    private String firstName;
    
    private String middleName;
    
    @NotNull(message="No lastname specified!")
    @Size(max=30,message="Lastname is to long")
    private String lastName;
    
    @NotNull(message="No email address specified!")
    @Size(max=256)
    @Pattern(regexp=".*@.*", message="No valid email adress specified!")
    private String emailAddress;
    
    @NotNull(message="No street specified!")
    private String street;
    
    @NotNull(message="No housenumber specified!")
    private String houseNumber;
    
    @NotNull(message="No postalcode specified!")
    @Pattern(regexp="[0-9]{4} [A-Za-z]{2}", message="Invalid postal code, please specify the postal code in the format: 9999 AA!")
    private String postalCode;
    
    @NotNull(message="No city specified!")
    private String city;
    
    @NotNull(message="Missing birthday!")
    @Past(message="The birthday cannot be in the future!")
    private Date birthday;
    
    @NotNull(message="No gender specified!")
    private Gender gender;

    @NotNull(message="Please specify the number of children!")
    @Min(value=0, message="Negative number of children is not really possible!")
    @Digits(integer=2,fraction=0)
    private Integer numberOfChildren;
    
    @AssertTrue(message="Without accepting the terms it's not possible to register!")
    private boolean acceptedTerms;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public boolean isAcceptedTerms() {
        return acceptedTerms;
    }

    public void setAcceptedTerms(boolean acceptedTerms) {
        this.acceptedTerms = acceptedTerms;
    }
    
    
}
