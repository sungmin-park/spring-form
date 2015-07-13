package com.github.parksungmin.spring.form.kotlin

import com.github.parksungmin.spring.form.Form
import com.github.parksungmin.spring.form.Response
import org.springframework.stereotype.Controller
import org.springframework.validation.Validator
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.WebRequest
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

Controller
RequestMapping("/user")
public class User {
    Inject
    var request: HttpServletRequest? = null

    Inject
    var validator: Validator? = null

    RequestMapping("/login.json")
    ResponseBody
    public fun login(ModelAttribute form: UserLoginForm): Response<String> {
        if (form.validateOnPost()) {
            return form.buildResponse(form.getId()!!)
        }
        return form.buildResponse()
    }

    InitBinder
    fun InitBinder(binder: WebDataBinder?, request: WebRequest?) {
        Form.beans.webBindingInitializer.request = this.request
        Form.beans.webBindingInitializer.validator = this.validator
        Form.beans.webBindingInitializer.initBinder(binder, request)
    }
}