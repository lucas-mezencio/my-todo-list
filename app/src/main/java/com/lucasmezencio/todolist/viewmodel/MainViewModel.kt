package com.lucasmezencio.todolist.viewmodel

import androidx.lifecycle.*
import com.lucasmezencio.todolist.datasource.repository.TaskRepository
import com.lucasmezencio.todolist.model.Task
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TaskRepository) : ViewModel() {
    val taskList: LiveData<List<Task>> = repository.getTaskList().asLiveData()

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }
}

class MainViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}