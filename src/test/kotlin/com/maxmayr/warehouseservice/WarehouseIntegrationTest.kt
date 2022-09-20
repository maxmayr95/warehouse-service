package com.maxmayr.warehouseservice

import org.hamcrest.Matchers
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class WarehouseIntegrationTest : BaseIntegrationTest() {
    @Autowired
    private val mvc: MockMvc? = null

    @Test
    fun `test search`() {
        mvc!!.perform(
            MockMvcRequestBuilders.get("/articles?q=test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize<Any>(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Is.`is`(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name", Is.`is`("test")))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    fun `test nothing found`() {
        mvc!!.perform(
            MockMvcRequestBuilders.get("/articles?q=example")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize<Any>(0)))
            .andDo(MockMvcResultHandlers.print());
    }

}