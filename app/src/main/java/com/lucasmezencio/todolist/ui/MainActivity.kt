package com.lucasmezencio.todolist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.lucasmezencio.todolist.application.TaskApplication
import com.lucasmezencio.todolist.databinding.ActivityMainBinding
import com.lucasmezencio.todolist.viewmodel.MainViewModel
import com.lucasmezencio.todolist.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CREATE_NEW_TASK = 100
    }

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        TaskListAdapter()
    }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as TaskApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTaskList.adapter = adapter

        mainViewModel.taskList.observe(this, Observer { tasks ->
            tasks.let {
                adapter.submitList(it)
            }
        })

        insertListeners()
        updateEmptyBackground()
    }

    private fun insertListeners() {
        addButtonListener()
        optionsMenuListeners()
    }

    private fun addButtonListener() {
        binding.fabAddButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }
    }

    private fun optionsMenuListeners() {
        adapter.listenerOptionsEdit = { task ->
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, task.id)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }
        adapter.listenerOptionsDelete = { task ->
//            TaskDataSource.deleteTask(task)
            updateEmptyBackground()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {
            updateEmptyBackground()
        }
    }

    private fun updateEmptyBackground() {
        binding.viewEmptyState.emptyState.visibility = if (mainViewModel.isTaskListEmpty) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}