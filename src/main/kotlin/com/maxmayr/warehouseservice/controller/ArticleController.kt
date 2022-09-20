package com.maxmayr.warehouseservice.controller

import com.maxmayr.warehouseservice.generated.api.ArticleApi
import com.maxmayr.warehouseservice.generated.model.SearchArticle200Response
import com.maxmayr.warehouseservice.service.ArticleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ArticleController(private val articleService: ArticleService): ArticleApi {

    override fun searchArticle(q: String?): ResponseEntity<SearchArticle200Response> {
        return ResponseEntity.ok(articleService.searchArticle(q!!))
    }
}