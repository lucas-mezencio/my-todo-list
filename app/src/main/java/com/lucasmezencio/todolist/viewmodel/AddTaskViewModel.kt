package com.lucasmezencio.todolist.viewmodel

import androidx.lifecycle.*
import com.lucasmezencio.todolist.datasource.repository.TaskRepository
import com.lucasmezencio.todolist.model.Task
import kotlinx.coroutines.launch

class AddTaskViewModel(private val repository: TaskRepository) : ViewModel() {

    var task: (Int) -> LiveData<Task> = { id ->
        repository.getTaskById(id).asLiveData()
    }

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
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