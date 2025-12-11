package com.ather.testassignment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ather.TaskItem

class FirstScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FirstScreenRepository(application)

    private val _taskList = MutableLiveData<List<TaskItem>>()
    val taskList: LiveData<List<TaskItem>> get() = _taskList

    init {
        loadTasks()
    }

    fun loadTasks() {
        _taskList.value = repository.getTasks()
    }
}
