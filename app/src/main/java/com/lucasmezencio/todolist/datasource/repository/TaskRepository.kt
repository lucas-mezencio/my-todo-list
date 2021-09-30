package com.lucasmezencio.todolist.datasource.repository

import com.lucasmezencio.todolist.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun insert(task: Task)

    fun getTaskById(id: Int): Flow<Task>

    fun getTaskList(): Flow<List<Task>>

    suspend fun deleteAll()

    suspend fun delete(id: Int)

    fun getTaskCount(): Flow<Int>
}