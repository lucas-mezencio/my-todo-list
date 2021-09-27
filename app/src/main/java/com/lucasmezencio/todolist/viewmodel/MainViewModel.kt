package com.lucasmezencio.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.lucasmezencio.todolist.datasource.repository.TaskRepository
import com.lucasmezencio.todolist.model.Task

class MainViewModel(private val repository: TaskRepository) : ViewModel() {

    val taskList: LiveData<List<Task>> = repository.getTaskList().asLiveData()
}

class MainViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}