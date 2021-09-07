package com.lucasmezencio.todolist.ui

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

        insertListeners()
    }

    private fun insertListeners() {
        binding.fabAddButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK) {
            binding.rvTaskList.adapter = adapter
            adapter.submitList(TaskDataSource.getList())
        }
    }
}