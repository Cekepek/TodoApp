package com.cekepek.todoapp.model

import android.webkit.WebSettings.RenderPriority
import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id") //cara memasukkan parameter ke query di depan parameter tambahkan (:) jadi :id
    fun selectTodo(id:Int): Todo

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid=:id")
    fun update(title:String, notes:String, priority: Int, id:Int)

    @Delete
    fun deleteTodo(todo:Todo)
}
