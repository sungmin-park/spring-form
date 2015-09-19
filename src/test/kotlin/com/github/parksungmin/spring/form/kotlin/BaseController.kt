package com.github.parksungmin.spring.form.kotlin

import com.github.parksungmin.spring.form.Form
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder

public open class BaseController {
    @InitBinder
    fun InitBinder(binder: WebDataBinder?) {
        Form.bean.bindingResult = binder!!.bindingResult
    }
}