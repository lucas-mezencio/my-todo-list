package com.lucasmezencio.todolist.extensions

import com.google.android.material.textfield.TextInputLayout
import com.lucasmezencio.todolist.model.Task
import java.text.SimpleDateFormat
import java.util.*


private val locale = Locale("pt", "BR")
fun Date.format(): String {
    return SimpleDateFormat("dd/MM/yyyy", locale).format(this)
}

var TextInputLayout.text: String
    get() = editText?.text?.toString() ?: ""
    set(value) {
        editText?.setText(value)
    }

fun RegistrationTaskParams.toTask(): Task {
    return Task(
        title = this.title,
        description = this.description,
        time = this.time,
        date = this.date
    )
}
