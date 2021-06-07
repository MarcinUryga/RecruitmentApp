package com.example.recruitmentapp.ui.recruitment.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.recruitmentapp.ui.recruitment.list.RecruitmentListViewModel
import com.example.recruitmentapp.ui.recruitment.list.model.Task

class TasksAdapter(private val viewModel: RecruitmentListViewModel) :
    ListAdapter<Task, TasksViewHolder>(TasksDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        return TasksViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }
}