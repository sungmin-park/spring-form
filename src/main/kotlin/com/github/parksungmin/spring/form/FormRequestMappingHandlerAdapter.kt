package com.github.parksungmin.spring.form

import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

public class FormRequestMappingHandlerAdapter : RequestMappingHandlerAdapter() {
    Inject var request: HttpServletRequest? = null
        get() {
            if ($request == null) {
                throw UnsupportedOperationException(
                        "Should register bean at least one of requestMappingHandlerAdapter, configurableWebBindingInitializer, webBindingInitializer"
                )
            }
            return $request
        }

    init {
        setWebBindingInitializer(Form.beans.webBindingInitializer)
    }
}