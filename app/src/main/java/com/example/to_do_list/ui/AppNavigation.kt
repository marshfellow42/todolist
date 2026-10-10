package com.example.to_do_list.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.to_do_list.viewmodel.TodoViewModel

object Routes {
    const val LIST = "list"
    const val DETAIL = "detail/{todoId}"
    fun detail(id: Int) = "detail/$id"
}

@Composable
fun AppNavigation(viewModel: TodoViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LIST,
        modifier = modifier
    ) {
        composable(Routes.LIST) {
            TodoScreen(
                viewModel = viewModel,
                onTodoClick = { navController.navigate(Routes.detail(it.id)) }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("todoId") ?: return@composable
            TodoDetailScreen(
                viewModel = viewModel,
                todoId = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}