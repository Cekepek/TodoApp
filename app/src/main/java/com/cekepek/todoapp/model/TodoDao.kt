package com.cekepek.todoapp.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id") //cara memasukkan parameter ke query di depan parameter tambahkan (:) jadi :id
    fun selectTodo(id:Int): Todo

    @Delete
    fun deleteTodo(todo:Todo)
}
