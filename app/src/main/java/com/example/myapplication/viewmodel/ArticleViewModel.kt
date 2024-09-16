package com.example.myapplication.viewmodel

import com.example.myapplication.model.Article
import com.example.myapplication.repository.ArticleRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    // Use LiveData for real-time updates
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    init {
        getArticles()
    }
    private fun getArticles() {
        viewModelScope.launch {
            try {
                val articleList = repository.getArticles()
                articleList.collect { articles ->
                    Log.d("ArticleViewModel", "Fetched articles: $articles")
                    _articles.postValue(articles)  // Post value to LiveData
                }
            } catch (e: Exception) {
                Log.e("ArticleViewModel", "Error fetching articles", e)
                _articles.postValue(emptyList())  // Handle error by posting an empty list
            }
        }
    }
}