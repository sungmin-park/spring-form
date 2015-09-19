package com.github.parksungmin.spring.form.kotlin

import com.github.parksungmin.spring.form.Response
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/user")
public class User : BaseController() {
    @RequestMapping("/login.json")
    @ResponseBody
    public fun login(form: UserLoginForm): Response<String?> {
        if (form.validateOnPost()) {
            return form.buildResponse(form.id)
        }
        return form.buildResponse()
    }
}