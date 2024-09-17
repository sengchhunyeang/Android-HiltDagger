package com.example.myapplication.datasource

import com.example.myapplication.model.Article
import com.example.myapplication.model.ArticleResponse
import com.example.myapplication.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class DatasourceImp @Inject constructor(
    private val apiService: ApiService
) : IDatasource {

    override suspend fun fetchArticles(): Response<ArticleResponse> {
        return apiService.fetchArticles()
    }

    override suspend fun deleteArticle(id: Int): Response<Unit> {
       return apiService.deleteArticle(id)
    }

    override suspend fun postArticle(article: Article): Response<ArticleResponse> {
       return apiService.postArticle(article)
    }

    override suspend fun updateArticle(id: Int, article: Article): Response<ArticleResponse> {
        return apiService.updateArticle(id, article)
    }
}
