package com.github.parksungmin.spring.form.kotlin;

import com.github.parksungmin.spring.form.Form;
import com.github.parksungmin.spring.form.Setter;
import org.hibernate.validator.constraints.NotBlank;

public class FormRejectForm extends Form {
    @NotBlank
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    @Setter
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean validate() {
        super.validate();
        if (!hasErrors("keyword")) {
            if (!"keyword".equals(getKeyword())) {
                reject("keyword", "keyword is not matched");
            }
        }
        return !hasErrors();
    }
}
