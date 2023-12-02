package com.example.todo.viewModel

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*


class DateViewModel() : ViewModel() {

    fun getDateNow(): String {
        val now = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일")

        return dateFormat.format(date)
    }
}
