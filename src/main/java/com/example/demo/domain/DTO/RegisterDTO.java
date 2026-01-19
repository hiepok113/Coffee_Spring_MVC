package com.example.demo.domain.DTO;

import com.example.demo.service.validator.RegisterChecked;

import jakarta.validation.constraints.Size;

@RegisterChecked
public class RegisterDTO {
    @Size(min=3,message="First name must be at least 3 characters long")
    private String firstName;
    private String lastName;
    @Size(min=5,message="Email must be at least 5 characters long")
    private String email;
    private String password;
    @Size(min=5,message="Confirm password must be at least 5 characters long")
    private String confirmPassword;

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    @Override
    public String toString() {
        return "RegisterDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
                + password + ", confirmPassword=" + confirmPassword + "]";
    }
}
