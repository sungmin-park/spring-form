package com.github.parksungmin.spring.form.kotlin;

import com.github.parksungmin.spring.form.Form;
import org.hibernate.validator.constraints.NotBlank;

public class UserLoginForm extends Form {
    @NotBlank
    private String id;
    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
