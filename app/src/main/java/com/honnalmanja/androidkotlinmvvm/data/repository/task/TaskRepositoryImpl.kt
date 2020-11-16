package com.honnalmanja.androidkotlinmvvm.data.repository.task

import com.honnalmanja.androidkotlinmvvm.data.model.app.TaskLiveData
import com.honnalmanja.androidkotlinmvvm.data.model.db.Task
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.AddTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.UpdateTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskCacheDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskLocalDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskRemoteDataSource
import com.honnalmanja.androidkotlinmvvm.domain.repository.TaskRepository
import com.honnalmanja.androidkotlinmvvm.utils.CommonUtil
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils
import java.lang.Exception

class TaskRepositoryImpl(
    private val taskRemoteDataSource: TaskRemoteDataSource,
    private val taskLocalDataSource: TaskLocalDataSource,
    private val taskCacheDataSource: TaskCacheDataSource,
): TaskRepository {

    private val TAG = "TaskRepositoryImpl"

    override suspend fun getAuthToken(): String? {
        return taskLocalDataSource.getAuthToken()
    }

    override suspend fun getAllTasks(): TaskLiveData {
        return getAllTaskFromCache()
    }

    private suspend fun getAllTaskFromCache(): TaskLiveData {

        val tasksList = taskCacheDataSource.getTaskList()
        return if (tasksList == null || tasksList.isEmpty()){
            val localTaskLiveData = getAllTaskFromDB()
            TaskLiveData(
                localTaskLiveData.statusCode, localTaskLiveData.message, null, localTaskLiveData.taskList
            )
        } else {
            TaskLiveData(200, "", null, tasksList)
        }
    }

    private suspend fun getAllTaskFromDB(): TaskLiveData {
        val taskList = taskLocalDataSource.getAllTask()
        return if (taskList == null || taskList.isEmpty()){
            getAllTaskFromAPI()
        } else {
            val tasksList = extractDBTaskToServerTask(taskList)
            // save to cache before sending
            taskCacheDataSource.saveTaskList(tasksList)
            TaskLiveData(
                200, "", null, tasksList
            )
        }
    }

    private suspend fun getAllTaskFromAPI(): TaskLiveData {

        return try {
            val response = taskRemoteDataSource.getAllTasks(taskLocalDataSource.getAuthToken())
            // save to local DB
            if(response.code() == 200){
                val taskList = ArrayList<Task>()
                response.body()?.taskList?.forEach{
                    LogUtils.logD(TAG, "taskID: ${it.taskID}")
                    taskList.add(
                        Task(
                        it.taskID,
                        it.taskDescription,
                        it.taskCompleted,
                        CommonUtil.convertDateToString(it.createdAt),
                        CommonUtil.convertDateToString(it.updatedAt)
                    ))
                }
                taskLocalDataSource.saveAllTasks(taskList)
                TaskLiveData(
                    response.code(), response.message(), null, response.body()?.taskList
                )
            } else {
                TaskLiveData(
                    response.code(), response.message(), null, null
                )
            }
        } catch (exception: Exception){
            LogUtils.logE(TAG, "getAllTaskFromAPI ${exception.message}")
            TaskLiveData(
                500, "Server not responded", null, null
            )
        }

    }

    override suspend fun getATasks(id: String?): TaskLiveData {
       val response = taskRemoteDataSource.getATask(taskLocalDataSource.getAuthToken(), id)
        return TaskLiveData(
            response.code(), response.message(), response.body(),null
        )
    }

    override suspend fun addATasks(addTaskRequest: AddTaskRequest): TaskLiveData {

        //Upload to server
        val response = taskRemoteDataSource.addTask(
            getAuthToken(), addTaskRequest
        )

        // Save to local database
        if(response.code() == 201 && response.body() != null){
            saveTaskToLocalDB(response.body()!!)
        } else {
            saveTaskToLocalDB(
                Tasks(
                "",
                    addTaskRequest.description,
                    addTaskRequest.isCompleted,
                    CommonUtil.getCurrentDateTime(),
                    CommonUtil.getCurrentDateTime()
            ))
        }

        // Update the cache
        val taskList = taskLocalDataSource.getAllTask()
        val tasksList = extractDBTaskToServerTask(taskList)
        taskCacheDataSource.saveTaskList(tasksList)

        return TaskLiveData(
            response.code(), response.message(), response.body(), null
        )

    }

    private suspend fun saveTaskToLocalDB(tasks: Tasks){
        val task = Task(
            tasks.taskID,
            tasks.taskDescription,
            tasks.taskCompleted,
            CommonUtil.convertDateToString(tasks.createdAt),
            CommonUtil.convertDateToString(tasks.updatedAt)
        )
        // Save to local database
        taskLocalDataSource.saveATask(task)
    }

    override suspend fun updateATasks(
        tasks: Tasks
    ): TaskLiveData {

        //Upload to server
        val response = taskRemoteDataSource.updateTask(
            getAuthToken(), tasks.taskID, UpdateTaskRequest(tasks.taskDescription, tasks.taskCompleted)
        )

        // Save to local database
        if(response.code() == 202 && response.body() != null){
            updateTaskToLocalDB(response.body()!!)
        } else {
            updateTaskToLocalDB(
                Tasks(
                    tasks.taskID,
                    tasks.taskDescription,
                    tasks.taskCompleted,
                    tasks.createdAt,
                    tasks.updatedAt
                ))
        }

        // Update the cache
        val taskList = taskLocalDataSource.getAllTask()
        val tasksList = extractDBTaskToServerTask(taskList)
        taskCacheDataSource.saveTaskList(tasksList)

        return TaskLiveData(
            response.code(), response.message(), response.body(), null
        )

    }

    private suspend fun updateTaskToLocalDB(tasks: Tasks){
        val task = Task(
            tasks.taskID,
            tasks.taskDescription,
            tasks.taskCompleted,
            CommonUtil.convertDateToString(tasks.createdAt),
            CommonUtil.convertDateToString(tasks.updatedAt)
        )
        // Save to local database
        taskLocalDataSource.updateATask(task.taskID, task.taskCompleted, task.taskDescription, task.taskCreatedAt)
    }

    override suspend fun deleteATask(id: String?) : TaskLiveData {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllTask() : TaskLiveData {
        TODO("Not yet implemented")
    }

    private fun extractDBTaskToServerTask(taskList: List<Task>?): List<Tasks> {
        val tasksList: ArrayList<Tasks> = ArrayList()
        taskList?.forEach { task ->
            tasksList.add(
                Tasks(
                    task.taskID,
                    task.taskDescription,
                    task.taskCompleted,
                    CommonUtil.convertStringToDate(task.taskCreatedAt),
                    CommonUtil.convertStringToDate(task.taskUpdatedAt)

                )
            )
        }
        return tasksList
    }

}