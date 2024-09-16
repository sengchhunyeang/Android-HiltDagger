package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Article
import com.example.myapplication.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

//
//@HiltViewModel
//class ArticleViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel() {
//    private val _articles = MutableStateFlow<List<Article>>(emptyList())
//    val articles: StateFlow<List<Article>> = _articles
//    init {
//        getArticles()
//    }
//    private fun getArticles() {
//        viewModelScope.launch {
//            try {
//                repository.getArticles().collect { articleList ->
//                    Log.d("ArticleViewModel", "Fetched articles: $articleList")
//                    _articles.value = articleList
//                }
//            } catch (e: Exception) {
//                Log.e("ArticleViewModel", "Error fetching articles", e)
//            }
//        }
//    }
//}
// Live Data
@HiltViewModel
class ArticleViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel() {
    val articles: LiveData<List<Article>> get() = repository.articles
    init {
        fetchArticles()
    }
    private fun fetchArticles() {
        viewModelScope.launch {
            repository.fetchArticles()
        }
    }
    fun deleteArticle(id: Int) {
        viewModelScope.launch {
            repository.deleteArticle(id)
        }
    }
}