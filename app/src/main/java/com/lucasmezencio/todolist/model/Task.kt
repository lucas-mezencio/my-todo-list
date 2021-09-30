package com.lucasmezencio.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val title: String,
    val description: String? = null,
    val time: String,
    val date: String
)