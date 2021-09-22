package com.lucasmezencio.todolist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lucasmezencio.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CREATE_NEW_TASK = 100
    }

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        TaskListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTaskList.adapter = adapter
        insertListeners()
        updateList()
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
            TaskDataSource.deleteTask(task)
            updateList()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {
            updateList()
        }
    }

    private fun updateList() {
        val list = TaskDataSource.getList()
        binding.viewEmptyState.emptyState.visibility = if (list.isNotEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
        adapter.submitList(list)
    }
}