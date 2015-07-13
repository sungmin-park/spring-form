package com.github.parksungmin.spring.form

import org.springframework.validation.BindingResult
import org.springframework.validation.Validator
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.support.WebBindingInitializer
import org.springframework.web.context.request.WebRequest
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

public class FormWebBindingInitializer : WebBindingInitializer {
    private val BINDING_RESULT = javaClass<Form>().getName() + ".bindingResult"
    Inject var validator: Validator? = null

    Inject var request: HttpServletRequest? = null
        get() = $request ?: Form.beans.configurableWebBindingInitializer.request

    public val bindingResult: BindingResult
        get() = request!!.getAttribute(BINDING_RESULT) as BindingResult

    override fun initBinder(binder: WebDataBinder?, request: WebRequest?) {
        this.request!!.setAttribute(BINDING_RESULT, binder!!.getBindingResult())
        println(this.request?.getAttribute(BINDING_RESULT))
    }
}
