package com.honnalmanja.androidkotlinmvvm.presentation.view.task

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.databinding.LayoutTaskListRowItemBinding
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils

class TaskAdapter(
    private val taskListener: TaskListener
): RecyclerView.Adapter<TaskViewHolder>() {

    private val taskList: ArrayList<Tasks> = ArrayList()

    fun setAllTasks(tasksList: List<Tasks>){
        taskList.clear()
        taskList.addAll(tasksList)
    }

    fun setATasks(position: Int, task: Tasks){
        taskList[position] = task
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: LayoutTaskListRowItemBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.layout_task_list_row_item, parent, false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindValues(taskList[position])
//        holder.itemHolder().setOnCheckedChangeListener { _, isChecked ->
//            taskList[holder.adapterPosition].taskCompleted = isChecked
//            taskListener.onTaskSelected(holder.adapterPosition, taskList[holder.adapterPosition])
//        }
        holder.itemHolder().setOnClickListener {
            val checked = taskList[holder.adapterPosition].taskCompleted
            taskList[holder.adapterPosition].taskCompleted = !checked
            taskListener.onTaskSelected(holder.adapterPosition, taskList[holder.adapterPosition])
        }
    }

    override fun getItemCount() = taskList.size
}