package com.example.todo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.dao.TodoDao
import com.example.todo.network.TodoRepository

class DateViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DateViewModel::class.java)) {
            return DateViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}