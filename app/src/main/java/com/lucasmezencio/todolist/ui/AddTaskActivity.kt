package com.lucasmezencio.todolist.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.lucasmezencio.todolist.databinding.ActivityAddTaskBinding
import com.lucasmezencio.todolist.datasource.TaskDataSource
import com.lucasmezencio.todolist.extensions.format
import com.lucasmezencio.todolist.extensions.text
import com.lucasmezencio.todolist.model.Task
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        dateListener()
        timeListener()
        newTaskButtonListener()
        cancelTaskButtonListener()
    }

    private fun dateListener() {
        binding.tinDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tinDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }
    }

    private fun timeListener() {
        binding.tinTime.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {
                val hour = if (timePicker.hour in 0..9) {
                    "0${timePicker.hour}"
                } else {
                    "${timePicker.hour}"
                }
                val minute = if (timePicker.minute in 0..9) {
                    "0${timePicker.minute}"
                } else {
                    "${timePicker.minute}"
                }
                binding.tinTime.text = "$hour:$minute"
            }
            timePicker.show(supportFragmentManager, null)
        }
    }

    private fun newTaskButtonListener() {
        binding.btnNewTask.setOnClickListener {
            val task = Task(
                binding.tinTitle.text,
                binding.tinTime.text,
                binding.tinDate.text
            )
            TaskDataSource.insertTask(task)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun cancelTaskButtonListener() {
        binding.btnCancelTask.setOnClickListener {
            finish()
        }
    }
}