package com.github.parksungmin.spring.form.kotlin

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import javax.inject.Inject
import kotlin.properties.Delegates

RunWith(SpringJUnit4ClassRunner::class)
ContextConfiguration(classes = arrayOf(WebMvcConfig::class))
WebAppConfiguration
public class FormTest {
    Inject
    private var wac: WebApplicationContext? = null
    private var mockMvc by Delegates.notNull<MockMvc>()

    private fun get(urlTemplate: String, vararg urlVariables: Any) =
            MockMvcRequestBuilders.get(urlTemplate, *urlVariables)!!

    private fun post(urlTemplate: String, vararg urlVariables: Any) =
            MockMvcRequestBuilders.post(urlTemplate, *urlVariables)!!

    private fun content() = MockMvcResultMatchers.content()

    private fun assertContent(url: String, content: String) {
        mockMvc.perform(get(url)).andExpect(content().string(content))
    }

    Before
    public fun before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    Test
    public fun testUserLogin() {
        assertContent("/user/login.json", """{"data":null,"errors":{}}""")
        mockMvc.perform(post("/user/login.json")).andExpect(MockMvcResultMatchers.content().string("""{"data":null,"errors":{"id":["may not be empty"],"password":["may not be empty"]}}"""))
        mockMvc.perform(post("/user/login.json?id=john&password=password"))
                .andExpect(MockMvcResultMatchers.content().string("""{"data":"john","errors":{}}"""))
    }

    Test
    public fun testFormReject() {
        mockMvc.perform(post("/form/reject")).andExpect(MockMvcResultMatchers.content().string("""{"data":null,"errors":{"keyword":["may not be empty"]}}"""))
        mockMvc.perform(post("/form/reject?keyword=badKeyword")).andExpect(MockMvcResultMatchers.content().string("""{"data":null,"errors":{"keyword":["keyword is not matched"]}}"""))
    }

    Test
    public fun testFormMethodOverride() {
        mockMvc.perform(post("/form/methodOverride")).andExpect(MockMvcResultMatchers.content().string("""true"""));
        mockMvc.perform(post("/form/methodOverride?_method=GET")).andExpect(MockMvcResultMatchers.content().string("""false"""));
    }
}