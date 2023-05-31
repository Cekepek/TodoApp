package com.cekepek.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cekepek.todoapp.util.MIGRRATION_1_2

@Database(entities = arrayOf(Todo::class), version =  2) //kalok isi nya ada selain tmmmmmmmmmmmmmmmodo tinggal tambah di sebelah Todo::class, , , dst
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile private var instance: TodoDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                TodoDatabase::class.java, "newtododb")
                .addMigrations(MIGRRATION_1_2)
                .build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}