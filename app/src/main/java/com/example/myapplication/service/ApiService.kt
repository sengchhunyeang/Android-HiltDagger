package com.example.myapplication.service

import com.example.myapplication.model.Article
import com.example.myapplication.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("api/v1/articles")
    suspend fun fetchArticles(): Response<ArticleResponse>
    @DELETE("api/v1/articles/{id}")
    suspend fun deleteArticle(@Path("id") id: Int): Response<Unit>
    @POST("/api/v1/articles") // Use the correct endpoint
    suspend fun postArticle(@Body article: Article): Response<ArticleResponse>

}
