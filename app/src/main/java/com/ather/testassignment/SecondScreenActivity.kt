package com.ather.testassignment

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var pref: SharedPrefHelper
    private lateinit var titleView: TextView
    private lateinit var descView: TextView
    private lateinit var statusView: TextView
    private lateinit var btnMarkComplete: Button

    private lateinit var taskTitle: String
    private var currentStatus: String = "Pending"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        pref = SharedPrefHelper(this)

        titleView = findViewById(R.id.tvDetailTitle)
        descView = findViewById(R.id.tvDetailDescription)
        statusView = findViewById(R.id.tvDetailStatus)
        btnMarkComplete = findViewById(R.id.btnMarkCompleted)

        taskTitle = intent.getStringExtra("title") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val incomingStatus = intent.getStringExtra("status") ?: "Pending"

        // Load saved status (if exists), else use the status sent
        currentStatus = pref.getStatus(taskTitle) ?: incomingStatus

        titleView.text = taskTitle
        descView.text = description
        updateStatus(currentStatus)
        updateButtonText(currentStatus)

        btnMarkComplete.setOnClickListener {
            // Toggle status
            currentStatus = if (currentStatus.equals("Pending", ignoreCase = true)) {
                "Completed"
            } else {
                "Pending"
            }

            // Save new status
            pref.saveStatus(taskTitle, currentStatus)
            updateStatus(currentStatus)
            updateButtonText(currentStatus)

            // Close screen to return to main
            finish()
        }
    }

    private fun updateStatus(status: String) {
        statusView.text = "Status: $status"
        statusView.setTextColor(
            if (status.equals("Completed", ignoreCase = true))
                Color.parseColor("#388E3C") // green
            else
                Color.parseColor("#D32F2F") // red
        )
    }

    private fun updateButtonText(status: String) {
        btnMarkComplete.text = if (status.equals("Pending", ignoreCase = true)) {
            "Mark as Completed"
        } else {
            "Mark as Pending"
        }
    }
}

