package com.softuni.gamestore.domain.entities.dtos;


import java.util.regex.Pattern;

import static com.softuni.gamestore.Constants.Validations.*;

public class UserRegisterDto {


    private String email;

    private String password;

    private String confirmPassword;

    private String fullName;

    public UserRegisterDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void validate(){
        boolean isEmailValid = Pattern.matches(EMAIL_PATTERN, this.email);

        if(!isEmailValid) {
            throw new IllegalArgumentException(NOT_VALID_EMAIL);
        }

        final boolean isPasswordValid = Pattern.matches(PASSWORD_PATTERN, this.password);

        if(!isPasswordValid) {
            throw new IllegalArgumentException(NOT_VALID_USERNAME_OR_PASSWORD);
        }

        if(!password.equals(confirmPassword)) {
            throw new IllegalArgumentException(PASSWORD_MISS_MATCH);
        }
    }

    public String successfulRegister() {
        return fullName + " was registered";
    }


}
