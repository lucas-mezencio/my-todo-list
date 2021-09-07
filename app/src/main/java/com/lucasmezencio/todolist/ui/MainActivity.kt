package com.lucasmezencio.todolist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucasmezencio.todolist.databinding.ActivityMainBinding
import com.lucasmezencio.todolist.datasource.TaskDataSource

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
        adapter.listenerOptionsEdit = {

        }
        adapter.listenerOptionsDelete = {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {
            updateList()
        }
    }

    private fun updateList() {
        adapter.submitList(TaskDataSource.getList())
    }
}