package com.cekepek.todoapp.util

import android.content.Context
import androidx.room.Room
import com.cekepek.todoapp.model.TodoDatabase

val DB_NAME = "newtododb"


fun buildDb(context: Context):TodoDatabase {
    val db = Room.databaseBuilder(context.applicationContext,
    TodoDatabase::class.java, DB_NAME).build()

    return db
}