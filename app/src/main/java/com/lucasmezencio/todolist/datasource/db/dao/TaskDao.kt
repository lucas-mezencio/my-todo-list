package com.lucasmezencio.todolist.datasource.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucasmezencio.todolist.datasource.db.TaskEntity

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createTask(task: TaskEntity)

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: Int): TaskEntity

    @Query("select * from tasks")
    fun getTaskList(): List<TaskEntity>
}