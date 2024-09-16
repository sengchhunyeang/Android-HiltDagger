package com.example.basicmvvm.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Article
import com.example.myapplication.model.ArticleResponse
import com.example.myapplication.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject



@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles
    fun fetchArticles() {
        viewModelScope.launch {
            try {
                val response = repository.fetchArticles()
                if (response.isSuccessful) {
                    _articles.postValue(response.body()?.payload ?: emptyList())
                    Log.d("ArticleViewModel", "Articles fetched successfully: ${response.isSuccessful} articles")
                } else {
                    Log.e("ArticleViewModel", "Error fetching articles: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ArticleViewModel", "Exception: $e")
            }
        }
    }
    fun deleteArticle(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.deleteArticle(id)
                if (response.isSuccessful) {
                    // Refresh the list after successful deletion
                    fetchArticles()

                } else {
                    Log.e("ArticleViewModel", "Error deleting article: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ArticleViewModel", "Exception: $e")
            }
        }
    }

}
