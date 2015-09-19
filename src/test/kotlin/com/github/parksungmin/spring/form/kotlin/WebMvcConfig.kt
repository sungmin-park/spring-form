package com.github.parksungmin.spring.form.kotlin

import com.github.parksungmin.spring.form.Form
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
@ComponentScan
public open class WebMvcConfig : WebMvcConfigurationSupport() {
    @Bean
    open fun springForm(): Form.bean = Form.bean
}