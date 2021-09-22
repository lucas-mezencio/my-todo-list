package com.lucasmezencio.todolist.application

import android.app.Application
import com.lucasmezencio.todolist.datasource.db.AppDatabase
import com.lucasmezencio.todolist.datasource.repository.TaskDbDataSource

class TaskApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { TaskDbDataSource(database.taskDao()) }
}