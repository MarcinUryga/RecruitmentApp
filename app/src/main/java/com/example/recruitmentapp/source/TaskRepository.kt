package com.example.recruitmentapp.source

import com.example.recruitmentapp.model.local.TaskEntity
import com.example.recruitmentapp.model.remote.TaskDto
import com.example.recruitmentapp.source.local.TasksLocalDataSource
import com.example.recruitmentapp.source.network.RecruitmentTaskApiService
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskApi: RecruitmentTaskApiService,
    private val tasksLocalDataSource: TasksLocalDataSource
) {
    fun fetchTasks(): Completable {
        return taskApi
            .getRecruitmentTask()
            .flatMapCompletable { updateDatabase(it) }
    }

    private fun updateDatabase(tasks: List<TaskDto>): Completable {
        return tasksLocalDataSource.let { it ->
            it.deleteTasks()
                .andThen(
                    tasksLocalDataSource.insertTask(tasks.map { transformTask(it) })
                )
        }
    }

    private fun transformTask(taskDto: TaskDto): TaskEntity {
        return TaskEntity(
            orderId = taskDto.orderId,
            title = taskDto.title,
            description = taskDto.description,
            imageUrl = taskDto.imageUrl,
            modificationDate = taskDto.modificationDate,
        )
    }

    fun getTasksFromDatabase(): Flowable<Result<List<TaskEntity>>> {
        return tasksLocalDataSource.getTasks()
    }
}