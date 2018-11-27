package com.github.parksungmin.spring.form

import org.springframework.validation.BindingResult

open public class Form {
    public val bindingResult: BindingResult
        get() = bean.bindingResult

    public fun isPost(): Boolean {
        var method = bean.request.getParameter("_method")
        if (method.isNullOrBlank()) {
            method = bean.request.method;
        }
        return method.equals("POST")
    }

    public fun validateOnPost(): Boolean {
        if (!isPost()) {
            return false;
        }
        return validate()
    }

    public open fun validate(): Boolean {
        bean.validator.validate(this, bindingResult)
        return !hasErrors()
    }

    public fun hasErrors(): Boolean {
        return bindingResult.hasErrors()
    }

    public fun hasErrors(vararg fields: String): Boolean {
        return fields.firstOrNull { bindingResult.getFieldErrorCount(it) != 0 } != null
    }

    public fun reject(name: String, message: String) {
        bindingResult.rejectValue(name, null, message)
    }

    @JvmOverloads
    public fun <T : Any> buildResponse(data: T? = null): Response<T?> {
        val errors: MutableMap<String, MutableList<String>> = hashMapOf()
        bindingResult.fieldErrors.forEach {
            val list = if (it.field !in errors) {
                val li = arrayListOf<String>()
                errors.put(it.field, li)
                li
            } else {
                errors.get(it.field)!!
            }
            list.add(it.defaultMessage)
        }
        return Response(data, errors.entries.sortedBy { it.key }.map { it.key to it.value }.toMap())
    }

    public object bean : FormBean()
}
