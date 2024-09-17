package com.example.myapplication.datasource

import com.example.myapplication.model.Article
import com.example.myapplication.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface IDatasource {
    suspend fun fetchArticles(): Response<ArticleResponse>
    suspend fun deleteArticle(@Path("id") id: Int): Response<Unit>
    suspend fun postArticle(article: Article):Response<ArticleResponse>
}
