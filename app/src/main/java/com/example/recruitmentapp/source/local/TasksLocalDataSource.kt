package com.example.recruitmentapp.source.local

import android.util.Log
import com.example.recruitmentapp.model.local.TaskEntity
import com.example.recruitmentapp.source.Result
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject


class TasksLocalDataSource @Inject constructor(
    private val tasksDatabase: TasksDatabase,
) {
    fun insertTask(taskEntities: List<TaskEntity>): Completable {
        return tasksDatabase.tasksDao()
            .insertTasks(taskEntities)
            .doOnError { Result.Error(Exception("Error during inserting task: $taskEntities")) }
            .doOnComplete { Log.d(TAG, "inserted task: $taskEntities") }
    }

    fun getTasks(): Flowable<Result<List<TaskEntity>>> {
        return tasksDatabase.tasksDao()
            .getTasks()
            .onErrorReturn { throwable ->
                Log.e(TAG, "getTasks: ", throwable)
                val task = TaskEntity().apply { orderId = -1 }
                return@onErrorReturn listOf(task)
            }
            .map { tasks ->
                if (tasks.isNotEmpty()) {
                    if (tasks.first().orderId == -1) {
                        return@map Result.Error(Exception("Error during getting tasks"))
                    }
                }
                return@map Result.Success(tasks)
            }
    }

    fun deleteTasks(): Completable {
        return tasksDatabase.tasksDao()
            .deleteTasks()
            .doOnError { Result.Error(Exception("Error during deleting tasks")) }
    }

    companion object {
        private val TAG = TasksLocalDataSource::class.simpleName
    }
}
