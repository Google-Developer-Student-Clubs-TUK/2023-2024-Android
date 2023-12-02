package com.example.todo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.dao.TodoDao
import com.example.todo.data.TodoEntity
import com.example.todo.network.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TodoViewModel(private val todoDao: TodoDao) : ViewModel() {

    private val repository: TodoRepository = TodoRepository(todoDao)

    private val _todos = MutableStateFlow<List<TodoEntity>>(emptyList())
    val todos: StateFlow<List<TodoEntity>> get() = _todos

    init {
        // 초기 데이터 로드
        loadTodos()
    }

    // 초기 데이터 로드
    private fun loadTodos() {
        viewModelScope.launch {
            repository.getAllTodos().collect { todoList ->
                _todos.value = todoList
            }
        }
    }


    fun insert(todo: TodoEntity) = viewModelScope.launch {
        repository.insert(todo)
    }

    fun update(todo: TodoEntity) = viewModelScope.launch {
        repository.update(todo)
    }

    fun delete(todo: TodoEntity) = viewModelScope.launch {
        repository.delete(todo)
    }
}
