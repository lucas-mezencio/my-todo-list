package com.lucasmezencio.todolist.application

import android.app.Application
import com.lucasmezencio.todolist.datasource.db.AppDatabase
import com.lucasmezencio.todolist.datasource.repository.TaskDbDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TaskApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { TaskDbDataSource(database.taskDao()) }
}