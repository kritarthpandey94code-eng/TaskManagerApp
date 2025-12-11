package com.ather.testassignment

import android.content.Context
import com.ather.TaskItem

class FirstScreenRepository(private val context: Context) {

    private val pref = SharedPrefHelper(context)

    fun getTasks(): List<TaskItem> {
        val list = listOf(
            TaskItem("Upload Documents", "Upload your KYC documents to proceed", "Pending"),
            TaskItem("Verification Completed", "Your account verification is done", "Completed"),
            TaskItem("Profile Setup", "Complete your basic profile details", "Pending"),
            TaskItem("Final Review", "Admin is reviewing your application", "Completed")
        )

        // Replace status with saved status if exists
        return list.map { task ->
            val saved = pref.getStatus(task.title)
            if (saved != null) task.copy(status = saved) else task
        }
    }
}

