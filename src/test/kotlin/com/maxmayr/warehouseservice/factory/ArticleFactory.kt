package com.maxmayr.warehouseservice.factory

import com.maxmayr.warehouseservice.model.ElasticSearchArticle

class ArticleFactory {

    fun getArticles(): List<ElasticSearchArticle> {
        var articles = mutableListOf<ElasticSearchArticle>()

        val elasticSearchArticle = ElasticSearchArticle()
        elasticSearchArticle.apply {
            id = 1
            name = "test"
        }
        articles.add(elasticSearchArticle)
        return articles
    }
}