package com.maxmayr.warehouseservice

import com.maxmayr.warehouseservice.factory.ArticleFactory
import com.maxmayr.warehouseservice.repository.WarehouseRepository
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("default", "integration-test")
@AutoConfigureMockMvc
class BaseIntegrationTest {

    @Autowired
    protected var warehouseRepository: WarehouseRepository? = null

    @BeforeEach
    fun init() {
        var persons = ArticleFactory().getArticles()
        persons.forEach {
            warehouseRepository?.save(it)
        }
    }
}