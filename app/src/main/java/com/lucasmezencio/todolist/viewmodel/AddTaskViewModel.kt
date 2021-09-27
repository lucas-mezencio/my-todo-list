package com.lucasmezencio.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lucasmezencio.todolist.datasource.repository.TaskRepository
import com.lucasmezencio.todolist.model.Task
import kotlinx.coroutines.launch

class AddTaskViewModel(private val repository: TaskRepository) : ViewModel() {

    var task: Task? = null

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun getTaskById(id: Int) = viewModelScope.launch {
        task = repository.getTaskById(id)
    }
}

class AddTaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
            return AddTaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}