package com.example.recruitmentapp.ui.recruitment.list

import com.example.recruitmentapp.extensions.findHtmlLinks
import com.example.recruitmentapp.model.local.TaskEntity
import com.example.recruitmentapp.source.Result
import com.example.recruitmentapp.source.TaskRepository
import com.example.recruitmentapp.ui.recruitment.list.model.Task
import com.example.recruitmentapp.utils.DateTimeFormatters
import com.example.recruitmentapp.utils.NetworkConnection
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val network: NetworkConnection
) {
    fun getTasks(): Completable {
        if (network.isConnected()) {
            return repository.fetchTasks()
        }
        return Completable.complete()
    }

    fun getTasksFromDb(): Flowable<Result<List<Task>>> {
        return repository.getTasksFromDatabase().map { it ->
            when (it) {
                is Result.Success -> {
                    if (it.data.isEmpty()) {
                        Result.Error(Exception("Empty list"))
                    } else {
                        Result.Success(
                            it.data
                                .map { task -> transformTask(task) }
                                .sortedBy { it.orderId }
                        )
                    }
                }
                is Result.Error -> it
                is Result.Loading -> it
            }
        }
    }

    private fun transformTask(task: TaskEntity): Task {
        val htmlLink = task.description.findHtmlLinks().first()
        return Task(
            description = task.description.replace(htmlLink, ""),
            imageUrl = task.imageUrl,
            modificationDate = DateTimeFormatters.parseByLocalize(task.modificationDate),
            orderId = task.orderId,
            title = task.title,
            descriptionUrl = htmlLink,
        )
    }
}