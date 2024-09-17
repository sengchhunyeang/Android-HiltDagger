package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.datasource.IDatasource
import com.example.myapplication.model.Article
import com.example.myapplication.model.ArticleResponse
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

    override suspend fun postArticle(article: Article): Response<ArticleResponse> {
        Log.d("POST_ARTICLE_REQUEST", "Posting article: $article")
        val response = apiService.postArticle(article)
        if (response.isSuccessful) {
            return response
        } else {
            val errorBody = response.errorBody()?.string()
            Log.e("POST_ARTICLE_ERROR", "Error posting article: $errorBody")
            throw Exception("Error posting article: $errorBody")
        }
    }

    override suspend fun updateArticle(id: Int, article: Article): Response<ArticleResponse> {
        val response=apiService.updateArticle(id,article)
        if (response.isSuccessful){
            return response
        }else{
            val errorBody = response.errorBody()?.string()
            Log.e("UPDATE_ARTICLE_ERROR", "Error posting article: $errorBody")
            throw Exception("Error Updating article: $errorBody")
        }
    }

}
