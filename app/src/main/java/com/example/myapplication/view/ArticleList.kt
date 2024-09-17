package com.example.myapplication.view

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.basicmvvm.ui.screen.ArticleViewModel
import com.example.myapplication.model.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleList( navController: NavHostController,viewModel: ArticleViewModel = hiltViewModel()) {
    // Observe LiveData from the ViewModel
    val articles by viewModel.articles.observeAsState(emptyList())

    // Fetch articles when the Composable first loads
    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }

    Scaffold(
        content = { padding ->
            if (articles.isEmpty()) {
                Text(
                    text = "No articles available.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .wrapContentSize()
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(articles) { article ->
                        ArticleItem(article = article, viewModel = viewModel)
                    }
                }
            }
        }
    )
}

@Composable
fun ArticleItem(article: Article, viewModel: ArticleViewModel) {
    Column(modifier = Modifier.padding(8.dp)) {
        if (article.imageUrl != null) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = article.content,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Author: ${article.author}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Published: ${article.publishedDate ?: "N/A"}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Views: ${article.views}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Published: ${if (article.isPublished) "Yes" else "No"}",
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = {  viewModel.deleteArticle(article.id) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Delete")
        }
        Button(
            onClick = {   },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
        ) {
            Text(text = "Update ")
        }
    }
}

