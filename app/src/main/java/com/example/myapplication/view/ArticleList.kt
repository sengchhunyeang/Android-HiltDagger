package com.example.myapplication.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.myapplication.model.Article
import com.example.myapplication.viewmodel.ArticleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleList(viewModel: ArticleViewModel = hiltViewModel()) {
//    val articles by viewModel.articles.collectAsState()
    val articles by viewModel.articles.observeAsState(emptyList())  // Observe LiveData

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
                        ArticleItem(article = article)
                    }
                }
            }
        }
    )
}

@Composable
fun ArticleItem(article: Article) {
    Column(modifier = Modifier.padding(8.dp)) {
        // Load and display image if imageUrl is not null
        if (article.imageUrl != null) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)  // Adjust height as needed
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Crop // Adjust content scale as needed
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
    }
}
