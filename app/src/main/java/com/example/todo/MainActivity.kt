package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.dao.TodoDao
import com.example.todo.network.TodoDatabase
import com.example.todo.screen.MainScreen
import com.example.todo.screen.WriteScreen
import com.example.todo.ui.theme.TodoTheme
import com.example.todo.viewModel.DateViewModel
import com.example.todo.viewModel.DateViewModelFactory
import com.example.todo.viewModel.TodoViewModel
import com.example.todo.viewModel.TodoViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var todoDao: TodoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todoDao = TodoDatabase.getDatabase(applicationContext).todoDao()

        setContent {
            TodoTheme {

                val todoViewModel = ViewModelProvider(
                    this,
                    TodoViewModelFactory(todoDao)
                ).get(TodoViewModel::class.java)

                val dateViewModel = ViewModelProvider(
                    this,
                    DateViewModelFactory()
                ).get(DateViewModel::class.java)

                val navController = rememberNavController()

                // NavHost 설정
                NavHost(navController = navController, startDestination = "mainScreen") {
                    composable("mainScreen") {
                        MainScreen(todoViewModel = todoViewModel, dateViewModel = dateViewModel, navController = navController)
                    }
                    composable("secondScreen") {
                        WriteScreen(todoViewModel = todoViewModel, navController = navController)
                    }
                }
            }
        }
    }
}