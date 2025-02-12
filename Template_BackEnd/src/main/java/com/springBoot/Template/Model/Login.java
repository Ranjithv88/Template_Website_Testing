package com.springBoot.Template.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Login model
public class Login {

    @NotBlank(message = " UserName is Mandatory ......! ")
    @Size(min = 2,message = " UserName Must Be Between 2 & 30 Characters .......! ",max = 40)
    private String userName;

    @NotBlank(message = " Password is Mandatory ......! ")
    @Size(min = 8,message = " Too Weak .......! ")
    private String password;


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

}

