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

class ListTodoViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        loadingLD.value = true
        todoLoadErrorLD.value = false

        launch {
            val db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java,
                "tododb").build()

            todoLD.postValue(db.todoDao().selectAllTodo()) //kalau data banyak pakai .postvalue kalo cuma 1 langsung .value
        }
    }

    fun clearTask(todo: Todo) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                TodoDatabase::class.java, "newtododb").build()
            db.todoDao().deleteTodo(todo)

            todoLD.postValue(db.todoDao().selectAllTodo())
        }
    }
}