package com.example.recruitmentapp.ui.recruitment.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.recruitmentapp.ui.recruitment.list.model.Task

class TasksDiffUtil : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.orderId == newItem.orderId
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}