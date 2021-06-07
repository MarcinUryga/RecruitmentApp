package com.example.recruitmentapp.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recruitmentapp.model.local.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object {
        const val DB_NAME = "Tasks.db"
    }
}