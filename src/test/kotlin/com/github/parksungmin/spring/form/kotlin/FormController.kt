package com.github.parksungmin.spring.form.kotlin

import com.github.parksungmin.spring.form.Form
import com.github.parksungmin.spring.form.Response
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/form")
class FormController : BaseController() {
    @RequestMapping("/reject")
    @ResponseBody
    fun reject(form: FormRejectForm): Response<String?> {
        if (form.validateOnPost()) {
            return form.buildResponse(form.keyword)
        }
        return form.buildResponse()
    }

    @RequestMapping("/methodOverride")
    @ResponseBody
    fun methodOverride(form: Form): Boolean {
        return form.isPost()
    }
}

