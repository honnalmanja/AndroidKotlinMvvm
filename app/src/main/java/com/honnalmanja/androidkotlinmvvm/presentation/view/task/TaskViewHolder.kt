package com.honnalmanja.androidkotlinmvvm.presentation.view.task

import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.databinding.LayoutTaskListRowItemBinding

class TaskViewHolder(private val binding: LayoutTaskListRowItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bindValues(tasks: Tasks){
        binding.task = tasks
    }

    fun itemHolder(): AppCompatCheckBox {
        return binding.taskDescriptionCb
    }

}