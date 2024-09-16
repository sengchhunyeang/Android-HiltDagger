package com.example.myapplication.repository




import android.util.Log
import com.example.myapplication.model.Article
import com.example.myapplication.service.ApiService

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val apiService: ApiService) {
    fun getArticles(): Flow<List<Article>> = flow {
        try {
            val response = apiService.getArticles()
            Log.d("ArticleRepository", "Response: ${response.payload}")
            emit(response.payload)
        } catch (e: Exception) {
            Log.e("ArticleRepository", "Error fetching articles", e)
            emit(emptyList())
        }
    }
}
