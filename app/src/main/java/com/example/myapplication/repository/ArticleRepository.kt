package com.example.myapplication.repository




import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Article
import com.example.myapplication.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val apiService: ApiService) {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    // Suspend function to fetch articles
    suspend fun fetchArticles() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getArticles()
                Log.d("ArticleRepository", "Response: ${response.payload}")
                _articles.postValue(response.payload)
            } catch (e: Exception) {
                Log.e("ArticleRepository", "Error fetching articles", e)
                _articles.postValue(emptyList())
            }
        }

    }

    // Delete an article by ID
    suspend fun deleteArticle(id: Int) {
        withContext(Dispatchers.IO) {
            try {
                apiService.deleteArticle(id)
                // Optionally, fetch articles again to refresh the list
                fetchArticles()
            } catch (e: Exception) {
                Log.e("ArticleRepository", "Error deleting article", e)
            }
        }
    }
}

//import android.util.Log
//import com.example.myapplication.model.Article
//import com.example.myapplication.service.ApiService
//
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import javax.inject.Inject
//
//class ArticleRepository @Inject constructor(private val apiService: ApiService) {
//    fun getArticles(): Flow<List<Article>> = flow {
//        try {
//            val response = apiService.getArticles()
//            Log.d("ArticleRepository", "Response: ${response.payload}")
//            emit(response.payload)
//        } catch (e: Exception) {
//            Log.e("ArticleRepository", "Error fetching articles", e)
//            emit(emptyList())
//        }
//    }
//}
