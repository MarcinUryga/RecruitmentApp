package com.example.recruitmentapp.ui.recruitment.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recruitmentapp.databinding.TaskItemBinding
import com.example.recruitmentapp.ui.recruitment.list.RecruitmentListViewModel
import com.example.recruitmentapp.ui.recruitment.list.model.Task

class TasksViewHolder private constructor(private val binding: TaskItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task, viewModel: RecruitmentListViewModel) {
        binding.viewmodel = viewModel
        binding.task = task
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): TasksViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = TaskItemBinding.inflate(layoutInflater, parent, false)

            return TasksViewHolder(binding)
        }
    }
}