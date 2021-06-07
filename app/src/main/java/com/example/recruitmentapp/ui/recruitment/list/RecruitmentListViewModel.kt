package com.example.recruitmentapp.ui.recruitment.list

import androidx.lifecycle.*
import com.example.recruitmentapp.Event
import com.example.recruitmentapp.source.Result
import com.example.recruitmentapp.ui.recruitment.list.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RecruitmentListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    private var _tasks: MediatorLiveData<Result<List<Task>>> = MediatorLiveData()
    var tasks: LiveData<Result<List<Task>>> = _tasks

    private val _forceRefresh = MutableLiveData(false)
    var forceRefresh: LiveData<Boolean> = _forceRefresh

    private val _openTaskDetailsEvent = MutableLiveData<Event<Task>>()
    var openTaskDetailsEvent: LiveData<Event<Task>> = _openTaskDetailsEvent

    init {
        disposables.add(
            fetchTasks()
                .doOnSubscribe { _tasks.value = Result.Loading }
                .andThen { _tasks = getTaskChangeObserver() }
                .subscribe({}, { error ->
                    Result.Error(Exception(error.message))
                })
        )
    }

    private fun getTaskChangeObserver(): MediatorLiveData<Result<List<Task>>> {
        _tasks.value = Result.Loading
        _tasks.addSource(getTasksSource()) {
            _tasks.value = it
        }
        return _tasks
    }

    private fun getTasksSource(): LiveData<Result<List<Task>>> {
        return LiveDataReactiveStreams.fromPublisher(
            getTasksUseCase.getTasksFromDb()
                .subscribeOn(Schedulers.io())
        )
    }

    fun onRefresh() {
        disposables.add(fetchTasks()
            .doOnSubscribe { _forceRefresh.value = true }
            .doFinally { _forceRefresh.value = false }
            .subscribe({}, { error ->
                Result.Error(Exception(error.message))
            }))
    }

    private fun fetchTasks() = getTasksUseCase.getTasks()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    fun openTaskDetails(task: Task) {
        _openTaskDetailsEvent.value = Event(task)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}