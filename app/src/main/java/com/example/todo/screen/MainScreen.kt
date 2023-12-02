package com.example.todo.screen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo.viewModel.DateViewModel
import com.example.todo.viewModel.TodoViewModel
import com.example.todo.R

@Composable
fun MainScreen(
    todoViewModel: TodoViewModel,
    dateViewModel: DateViewModel,
    navController: NavController
) {
    Box(
        modifier = Modifier.background(Color.White).fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.teddy_bear),
            contentDescription = "Teddy Baer",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
        )
        Column(
        ) {
            TopAppBar(
                title = {
                    Text(
                        "Todo List",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                backgroundColor = Color.White,
                elevation = 0.dp,
                actions = {

                    IconButton(onClick = {
                        navController.navigate("secondScreen")
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "추가하깅")
                    }
                }
            )
            Box(
                modifier = Modifier.padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                Column() {
                    TodayDate(today = dateViewModel.getDateNow())
                    Spacer(modifier = Modifier.padding(bottom = 15.dp))
                    TodoItem(viewModel = todoViewModel)
                }
            }
        }
    }
}