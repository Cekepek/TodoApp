package com.cekepek.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.cekepek.todoapp.model.Todo
import com.cekepek.todoapp.model.TodoDatabase
import com.cekepek.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope
{
    val todoLd = MutableLiveData<Todo>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.IO

    fun addTodo (todo:Todo){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(todo)
        }
    }

    fun fetch(uuid: Int){
        launch {
            val db = buildDb(getApplication())
            todoLd.postValue(db.todoDao().selectTodo(uuid))
        }
    }
}