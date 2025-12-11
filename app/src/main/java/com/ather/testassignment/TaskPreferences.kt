package com.ather.testassignment

import android.content.Context

class TaskPreferences(context: Context) {

    private val sharedPref =
        context.getSharedPreferences("Task_Status", Context.MODE_PRIVATE)

    fun setTaskStatus(taskId: String, status: String) {
        sharedPref.edit().putString(taskId, status).apply()
    }

    fun getTaskStatus(taskId: String): String {
        return sharedPref.getString(taskId, "pending") ?: "pending"
    }
}
