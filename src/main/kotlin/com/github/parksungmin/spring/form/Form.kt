package com.github.parksungmin.spring.form

import org.springframework.validation.BindingResult

abstract public class Form {
    public val bindingResult: BindingResult
        get() = bean.bindingResult

    public fun isPost(): Boolean {
        return bean.request!!.getMethod().equals("POST")
    }

    public fun validateOnPost(): Boolean {
        if (!isPost()) {
            return false;
        }
        return validate()
    }

    public fun validate(): Boolean {
        return !hasErrors()
    }

    public fun hasErrors(): Boolean {
        bean.validator!!.validate(this, bindingResult)
        return bindingResult.hasErrors()
    }

    jvmOverloads
    public fun <T> buildResponse(data: T = null): Response<T> {
        val errors: MutableMap<String, MutableList<String>> = hashMapOf<String, MutableList<String>>()
        bindingResult.getFieldErrors().forEach {
            val list = if (it.getField() !in errors) {
                val li = arrayListOf<String>()
                errors.put(it.getField(), li)
                li
            } else {
                errors.get(it.getField())!!
            }
            list.add(it.getDefaultMessage())
        }
        return Response(data, errors)
    }

    public object bean : FormBean()
}
