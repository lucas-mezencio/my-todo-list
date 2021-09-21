package com.lucasmezencio.todolist.datasource.repository

import com.lucasmezencio.todolist.datasource.db.dao.TaskDao
import com.lucasmezencio.todolist.extensions.RegistrationTaskParams
import com.lucasmezencio.todolist.extensions.toTask
import com.lucasmezencio.todolist.extensions.toTaskEntity
import com.lucasmezencio.todolist.model.Task
import kotlinx.coroutines.flow.Flow

class TaskDbDataSource(
    private val taskDao: TaskDao
) :  TaskRepository{
    override fun insert(registrationTaskParams: RegistrationTaskParams) {
        val taskEntity = registrationTaskParams.toTaskEntity()
        taskDao.createTask(taskEntity)
    }

    override fun getTaskById(id: Int): Task {
        return taskDao.getTaskById(id).toTask()
    }

    override fun getTaskList(): Flow<List<Task>> {
        TODO("Not yet implemented")
    }
}