package com.example.todo.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.data.TodoEntity
import com.example.todo.viewModel.TodoViewModel


@Composable
fun TodayDate(today: String) {
    Column(
    ) {
        Text(text = today, fontWeight = FontWeight.SemiBold, color = Color(0xff878787))
    }
}

@Composable
fun WriteTodo(viewModel: TodoViewModel, navController: NavController) {

    var newTodo by remember { mutableStateOf("") }

    OutlinedTextField(
        value = newTodo,
        onValueChange = { newTodo = it },
        modifier = Modifier
            .fillMaxWidth()
    )

    Spacer(modifier = Modifier.padding(bottom = 10.dp))

    // 버튼 클릭 시 새로운 할 일 추가
    Button(
        onClick = {
            viewModel.insert(TodoEntity(title = newTodo, isCompleted = false))
            newTodo = ""
            navController.navigateUp()
        },
        modifier = Modifier
            .height(40.dp)
    ) {
        Text("Add Todo")
    }
}


@Composable
fun TodoItem(viewModel: TodoViewModel) {
    val todos by viewModel.todos.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(todos) { todo ->
            Column() {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0x70FDF7F3)).padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = todo.isCompleted,
                            onCheckedChange = {
                                val updatedTodo = todo.copy(isCompleted = it)
                                viewModel.update(updatedTodo)
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = todo.title,
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xff656565)
                            ),
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(
                            onClick = {
                                viewModel.delete(todo)
                            }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }

            }
        }
    }
}
