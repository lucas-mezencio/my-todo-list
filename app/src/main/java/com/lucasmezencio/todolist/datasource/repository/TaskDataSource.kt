package com.lucasmezencio.todolist.datasource.repository

import com.lucasmezencio.todolist.model.Task


// todo: Remove this file
object TaskDataSource {
    private val list = arrayListOf<Task>()

    fun getList() = list.toList()

    fun insertTask(task: Task) {
        if (task.id == 0) {
            list.add(task.copy(id = list.size + 1))
        } else {
            list.remove(task)
            list.add(task)
        }
    }

    fun findById(id: Int) = list.find { it.id == id }

    fun deleteTask(task: Task) {
        list.remove(task)
    }
}