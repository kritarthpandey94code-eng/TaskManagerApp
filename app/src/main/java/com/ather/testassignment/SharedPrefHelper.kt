package com.ather.testassignment

import android.content.Context

class SharedPrefHelper(context: Context) {

    private val sharedPref = context.getSharedPreferences("task_prefs", Context.MODE_PRIVATE)

    fun saveStatus(title: String, status: String) {
        sharedPref.edit().putString(title, status).apply()
    }

    fun getStatus(title: String): String? {
        return sharedPref.getString(title, null)
    }
}
