package com.maxmayr.warehouseservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.elasticsearch.annotations.Document

@TypeAlias("p")
@Document(indexName = "article-index")
class ElasticSearchArticle(
    @Id
    var id: Long? = null,
    var name: String? = null
)