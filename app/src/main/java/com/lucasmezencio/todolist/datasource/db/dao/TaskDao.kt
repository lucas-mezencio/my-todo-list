package com.lucasmezencio.todolist.datasource.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucasmezencio.todolist.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: Int): Flow<Task>

    @Query("select * from tasks")
    fun getTaskList(): Flow<List<Task>>

    @Query("DELETE FROM tasks")
    suspend fun deleteAll()
}