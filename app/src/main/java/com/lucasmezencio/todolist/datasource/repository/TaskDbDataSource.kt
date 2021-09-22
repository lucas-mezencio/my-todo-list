package com.lucasmezencio.todolist.datasource.repository

import com.lucasmezencio.todolist.datasource.db.dao.TaskDao
import com.lucasmezencio.todolist.extensions.RegistrationTaskParams
import com.lucasmezencio.todolist.extensions.toTask
import com.lucasmezencio.todolist.model.Task
import kotlinx.coroutines.flow.Flow

class TaskDbDataSource(
    private val taskDao: TaskDao
) :  TaskRepository{
    override suspend fun insert(registrationTaskParams: RegistrationTaskParams) {
        val task = registrationTaskParams.toTask()
        taskDao.createTask(task)
    }

    override fun getTaskById(id: Int): Task = taskDao.getTaskById(id)

    override fun getTaskList(): Flow<List<Task>> = taskDao.getTaskList()
}