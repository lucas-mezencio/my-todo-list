package com.lucasmezencio.todolist.datasource.repository

import com.lucasmezencio.todolist.datasource.db.dao.TaskDao
import com.lucasmezencio.todolist.model.Task
import kotlinx.coroutines.flow.Flow

class TaskDbDataSource(
    private val taskDao: TaskDao
) :  TaskRepository{
    override suspend fun insert(task: Task) {
        taskDao.createTask(task)
    }

    override fun getTaskById(id: Int): Task = taskDao.getTaskById(id)

    override fun getTaskList(): Flow<List<Task>> = taskDao.getTaskList()
}