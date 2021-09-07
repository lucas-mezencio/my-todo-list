package com.lucasmezencio.todolist.datasource

import com.lucasmezencio.todolist.model.Task

object TaskDataSource {
    private val list = arrayListOf<Task>()

    fun getList() = list.toList()

    fun insertTask(task: Task) {
        list.add(task.copy(id = list.size + 1))
    }
}