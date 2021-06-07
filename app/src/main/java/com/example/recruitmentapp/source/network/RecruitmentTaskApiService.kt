package com.example.recruitmentapp.source.network

import com.example.recruitmentapp.model.remote.TaskDto
import io.reactivex.Single
import retrofit2.http.GET

interface RecruitmentTaskApiService {
    @GET("recruitment-task")
    fun getRecruitmentTask(): Single<List<TaskDto>>
}