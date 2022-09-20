package com.maxmayr.warehouseservice.service

import com.maxmayr.warehouseservice.generated.model.Article
import com.maxmayr.warehouseservice.generated.model.SearchArticle200Response
import com.maxmayr.warehouseservice.model.ElasticSearchArticle
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ArticleService(private val elasticsearchRestTemplate: ElasticsearchRestTemplate) {

    fun searchArticle(q: String): SearchArticle200Response {
        val searchQuery = generateQuery(q)
        val elasticsearchResult = elasticsearchRestTemplate.search(searchQuery, ElasticSearchArticle::class.java)
        val resultAricle = SearchArticle200Response()
        val resultList = mapResult(elasticsearchResult)
        resultAricle.content = resultList
        return resultAricle
    }
    private fun mapResult(elasticsearchResult: SearchHits<ElasticSearchArticle>): List<Article>? {
        return elasticsearchResult.stream().map { hit ->
            val article = Article()
            article.apply {
                id = hit.content.id
                name = hit.content.name
            }
        }.collect(Collectors.toList())
    }
    private fun generateQuery(q: String): NativeSearchQuery {
        var boolQuery = QueryBuilders.boolQuery()
        boolQuery.must(QueryBuilders.queryStringQuery(q).defaultField("*"))
        return NativeSearchQueryBuilder()
            .withQuery(boolQuery)
            .withPageable(PageRequest.of(0, 10))
            .build()
    }
}