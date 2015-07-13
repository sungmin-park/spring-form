package com.github.parksungmin.spring.form

import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer
import org.springframework.web.context.request.WebRequest
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest
import javax.xml.validation.Validator

public class FormConfigurableWebBindingInitializer : ConfigurableWebBindingInitializer() {
    Inject var request: HttpServletRequest? = null
        get() = $request ?: Form.beans.requestMappingHandlerAdapter.request

    override fun initBinder(binder: WebDataBinder?, request: WebRequest?) {
        super.initBinder(binder, request)
        Form.beans.webBindingInitializer.initBinder(binder, request)
    }
}
