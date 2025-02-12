package com.springBoot.Template.Model;

import com.springBoot.Template.Annotations.Decimal;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Register Model
public class Register {

    @NotBlank(message = " UserName is Mandatory ......! ")
    @Size(min = 2,message = " UserName Must Be Between 2 & 30 Characters .......! ",max = 40)
    private String userName;

    @NotBlank(message = " Password is Mandatory ......! ")
    @Size(min = 8,message = " Too Weak .......! ")
    private String password;

    // this is Age Column
    @Decimal
    private int age;

    @NotBlank(message = " Email is Mandatory ......! ")
    @Email(message = " Email is Invalid .......! ")
    @Column(unique=true)
    private String email;

    @Column(unique = true)
    @NotNull(message = " Phone Number is Mandatory ......! ")
    @Size(min = 10,max = 10,message = " Phone Number is Invalid ......! ")
    private String phoneNumber;

    // Getter and Setter For Private Variables
    public @NotBlank(message = " UserName is Mandatory ......! ") @Size(min = 2, message = " UserName Must Be Between 2 & 30 Characters .......! ", max = 40) String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank(message = " UserName is Mandatory ......! ") @Size(min = 2, message = " UserName Must Be Between 2 & 30 Characters .......! ", max = 40) String userName) {
        this.userName = userName;
    }

    public @NotBlank(message = " Password is Mandatory ......! ") @Size(min = 8, message = " Too Weak .......! ") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = " Password is Mandatory ......! ") @Size(min = 8, message = " Too Weak .......! ") String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public @NotBlank(message = " Email is Mandatory ......! ") @Email(message = " Email is Invalid .......! ") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = " Email is Mandatory ......! ") @Email(message = " Email is Invalid .......! ") String email) {
        this.email = email;
    }

    public @NotNull(message = " Phone Number is Mandatory ......! ") @Size(min = 10, max = 10, message = " Phone Number is Invalid ......! ") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull(message = " Phone Number is Mandatory ......! ") @Size(min = 10, max = 10, message = " Phone Number is Invalid ......! ") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

