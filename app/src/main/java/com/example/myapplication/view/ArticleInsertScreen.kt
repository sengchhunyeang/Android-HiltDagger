import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.basicmvvm.ui.screen.ArticleViewModel
import com.example.myapplication.model.Article
import com.example.myapplication.route.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleInsertScreen(
    navController: NavHostController,
    articleViewModel: ArticleViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var content by remember { mutableStateOf(TextFieldValue("")) }
    var imageUrl by remember { mutableStateOf(TextFieldValue("")) }
    var author by remember { mutableStateOf(TextFieldValue("")) }
    var publishedDate by remember { mutableStateOf(TextFieldValue("")) }
    var views by remember { mutableStateOf(TextFieldValue("")) }
    var isPublished by remember { mutableStateOf(true) } // Default to true, can add a Toggle for user input

    val articles by articleViewModel.articles.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Article Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Article Content") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            maxLines = 4
        )

        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("Image URL") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = publishedDate,
            onValueChange = { publishedDate = it },
            label = { Text("Published Date") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = views,
            onValueChange = { views = it },
            label = { Text("Views") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Published")
            Switch(
                checked = isPublished,
                onCheckedChange = { isPublished = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.text.isNotEmpty() && content.text.isNotEmpty()) {
                    val article = Article(
                        id = 0,
                        title = title.text,
                        content = content.text,
                        imageUrl = imageUrl.text.ifEmpty { null },
                        author = author.text,
                        publishedDate = publishedDate.text.ifEmpty { null },
                        views = views.text.toIntOrNull() ?: 0,
                        isPublished = isPublished
                    )
                    Log.d("REQUEST_PAYLOAD", "Posting article: $article")
                    articleViewModel.postArticle(article)
                    title = TextFieldValue("")
                    content = TextFieldValue("")
                    imageUrl = TextFieldValue("")
                    author = TextFieldValue("")
                    publishedDate = TextFieldValue("")
                    views = TextFieldValue("")
                    isPublished = true
                    navController.navigate(Screen.ArticleListScreen.route)
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit Article")
        }
    }
}


