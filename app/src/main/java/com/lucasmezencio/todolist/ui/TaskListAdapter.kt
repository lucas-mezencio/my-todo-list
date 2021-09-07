package com.lucasmezencio.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucasmezencio.todolist.R
import com.lucasmezencio.todolist.databinding.ItemTaskBinding
import com.lucasmezencio.todolist.model.Task

class TaskListAdapter() : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallback()) {

    var listenerOptionsEdit: (Task) -> Unit = {}
    var listenerOptionsDelete: (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Task) {
            binding.tvTitle.text = item.title
            binding.tvTime.text = "${item.date} ${item.time}"
            binding.ivOptions.setOnClickListener {
                showPopup(item)
            }
        }

        private fun showPopup(item: Task) {
            val ivOptions = binding.ivOptions
            val popupMenu = PopupMenu(ivOptions.context, ivOptions)
            popupMenu.menuInflater.inflate(R.menu.popup_options_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionEdit -> listenerOptionsEdit(item)
                    R.id.actionDelete -> listenerOptionsDelete(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.id == newItem.id

}
