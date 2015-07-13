package com.github.parksungmin.spring.form

import org.springframework.validation.BindingResult
import org.springframework.validation.Validator
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

public open class FormBean {
    private val BINDING_RESULT = javaClass<Form>().getName()
    Inject
    public var request: HttpServletRequest? = null
    Inject
    public var validator: Validator? = null

    public var bindingResult: BindingResult
        get() = request!!.getAttribute(BINDING_RESULT) as BindingResult
        set(bindingResult: BindingResult) = request!!.setAttribute(BINDING_RESULT, bindingResult)
}