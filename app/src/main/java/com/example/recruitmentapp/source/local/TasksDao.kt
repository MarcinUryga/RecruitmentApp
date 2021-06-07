package com.example.recruitmentapp.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recruitmentapp.model.local.TaskEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TasksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTasks(taskEntity: List<TaskEntity>): Completable

    @Query("SELECT * FROM Tasks")
    fun getTasks(): Flowable<List<TaskEntity>>

    @Query("DELETE FROM Tasks")
    fun deleteTasks(): Completable
}