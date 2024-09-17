package com.example.myapplication.route

import ArticleInsertScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.view.ArticleList


sealed class Screen(val route: String) {
    data object ArticleInsertScreen : Screen("article_insert_screen")
    data object ArticleListScreen : Screen("article_list_screen")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.ArticleInsertScreen.route) {
        composable(Screen.ArticleInsertScreen.route) {
            ArticleInsertScreen(navController=navController)
        }
        composable(Screen.ArticleListScreen.route) { // Ensure this route is defined
            ArticleList(navController = navController)
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}