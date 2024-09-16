package com.example.myapplication.repository

import com.example.myapplication.datasource.IDatasource
import com.example.myapplication.model.ArticleResponse
import com.example.myapplication.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val apiService: IDatasource
) : IRepository {

    override suspend fun fetchArticles(): Response<ArticleResponse> {
        return apiService.fetchArticles()
    }

    override suspend fun deleteArticle(id: Int): Response<Unit> {
        return apiService.deleteArticle(id)
    }

}
