package com.cekepek.todoapp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cekepek.todoapp.model.TodoDatabase

val DB_NAME = "newtododb"


fun buildDb(context: Context):TodoDatabase {
    val db = Room.databaseBuilder(context.applicationContext,
    TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2,MIGRATION_2_3,MIGRATION_3_4)
        .build()

    return db
}

//migration untk mengubah versi
val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL"
        )

        database.execSQL(
            "INSERT INTO todo(title, notes, priority) VALUES('Sample', 'Sample Notes',3)"
        )
    }
}

val MIGRATION_2_3 = object: Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL"
            //menggunakan integer karena sqlite tidak menyediakan tipe BOOLEAN sehingga menggunakan INTEGER 0 atau 1
        )

        database.execSQL(
            "INSERT INTO todo(title, notes, priority) VALUES('Sample2', 'Sample Notes2',0)"
        )
    }
}

val MIGRATION_3_4 = object: Migration(3,4){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN todo_date INTEGER DEFAULT 0 NOT NULL"
            //menggunakan integer karena sqlite tidak menyediakan tipe BOOLEAN sehingga menggunakan INTEGER 0 atau 1
        )

//        database.execSQL(
//            "INSERT INTO todo(title, notes, priority, is_done, todo_date) VALUES('Sample2', 'Sample Notes2',0, 0, )"
//        )
    }
}