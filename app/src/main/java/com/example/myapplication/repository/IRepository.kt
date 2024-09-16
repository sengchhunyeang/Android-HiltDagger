package com.example.myapplication.repository

import com.example.myapplication.model.ArticleResponse
import retrofit2.Response

interface IRepository {
    suspend fun fetchArticles(): Response<ArticleResponse>
    suspend fun deleteArticle(id: Int): Response<Unit>
}
