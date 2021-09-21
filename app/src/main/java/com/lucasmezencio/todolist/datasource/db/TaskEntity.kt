package com.lucasmezencio.todolist.datasource.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val title: String,
    val description: String,
    val time: String,
    val date: String
)