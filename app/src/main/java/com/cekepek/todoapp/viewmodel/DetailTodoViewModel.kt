package com.cekepek.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.cekepek.todoapp.model.Todo
import com.cekepek.todoapp.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope
{
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.IO

    fun addTodo (todo:Todo){
        launch {
            val db = Room.databaseBuilder(
                getApplication(), TodoDatabase::class.java,
                "newtododb").build()
            db.todoDao().insertAll(todo)
        }
    }

}