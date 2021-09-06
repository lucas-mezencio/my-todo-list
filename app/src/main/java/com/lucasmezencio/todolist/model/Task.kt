package com.lucasmezencio.todolist.model

data class Task(
    val title: String,
    val time: String,
    val date: String,
    val id: Int = 0
)
