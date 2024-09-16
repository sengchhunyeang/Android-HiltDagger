package com.example.myapplication.service
import com.example.myapplication.model.ArticleResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/api/v1/articles")
    suspend fun getArticles(): ArticleResponse
}

