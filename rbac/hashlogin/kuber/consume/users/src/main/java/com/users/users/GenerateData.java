package com.users.users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import java.io.Serializable;

public class GenerateData  {

//    private static final long serialVersionUID = 1L;
    
    Faker faker = new Faker();
    @JsonProperty("fname")
    String fname = faker.name().firstName();
    @JsonProperty("lname")
    String lname = faker.name().lastName();
    @JsonProperty("address")
    String address = faker.address().country();
    @JsonProperty("email")
    String email = fname+"@gmail.com";
    @JsonProperty("contact")
    String contact = faker.phoneNumber().cellPhone();

    // GETTERS

    public String GetFname() {
        return fname;
    }

    public String GetLname() {
        return lname;
    }

    public String GetAddress() {
        return address;
    }

    public String GetEmail() {
        return email;
    }

    public String GetContact() {
        return contact;
    }


}
