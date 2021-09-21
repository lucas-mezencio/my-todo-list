package com.lucasmezencio.todolist.datasource.repository

import com.lucasmezencio.todolist.extensions.RegistrationTaskParams
import com.lucasmezencio.todolist.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun createTask(registrationTaskParams: RegistrationTaskParams)

    fun getTaskById(id: Int): Task

    fun getTaskList(): Flow<List<Task>>
}