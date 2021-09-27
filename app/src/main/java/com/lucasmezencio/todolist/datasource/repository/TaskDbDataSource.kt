package com.lucasmezencio.todolist.datasource.repository

import androidx.lifecycle.LiveData
import com.lucasmezencio.todolist.datasource.db.dao.TaskDao
import com.lucasmezencio.todolist.model.Task
import kotlinx.coroutines.flow.Flow

class TaskDbDataSource(
    private val taskDao: TaskDao
) :  TaskRepository{
    override suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    override suspend fun getTaskById(id: Int): Task = taskDao.getTaskById(id)

    override fun getTaskList(): Flow<List<Task>> = taskDao.getTaskList()

    override suspend fun deleteAll() {
        taskDao.deleteAll()
    }

    override suspend fun getLastTask(): LiveData<Task> = taskDao.getLastTask()



}