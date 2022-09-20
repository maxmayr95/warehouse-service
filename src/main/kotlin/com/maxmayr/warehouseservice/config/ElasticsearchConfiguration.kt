package com.maxmayr.warehouseservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories


@Configuration("elastic")
@EnableElasticsearchRepositories(basePackages = ["com.maxmayr.warehouseservice.repository"])
class ElasticsearchConfiguration {
    @Value("\${com.maxmayr.warehouseservice.read-alias}")
    private lateinit var indexName: String

    fun getIndexName(): String? {
        return indexName
    }
}