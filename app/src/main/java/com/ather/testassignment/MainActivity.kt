package com.ather.testassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ather.TaskItem
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnFilter: Button
    private lateinit var adapter: SampleAdapter
    private lateinit var viewModel: FirstScreenViewModel
    private lateinit var prefs: SharedPrefHelper

    private var fullList: List<TaskItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupRecyclerView()

        prefs = SharedPrefHelper(this)
        btnFilter = findViewById(R.id.btnFilter)

        viewModel = ViewModelProvider(this)[FirstScreenViewModel::class.java]

        viewModel.taskList.observe(this) { tasks ->
            fullList = tasks
            adapter.updateList(tasks)
        }

        btnFilter.setOnClickListener { showFilterDialog() }
    }

    private fun showFilterDialog() {
        val options = arrayOf("All", "Completed", "Pending")

        AlertDialog.Builder(this)
            .setTitle("Select Filter")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showAll()
                    1 -> showCompleted()
                    2 -> showPending()
                }
            }
            .show()
    }

    private fun showAll() {
        adapter.updateList(fullList)
    }

    private fun showCompleted() {
        val filtered = fullList.filter { task ->
            prefs.getStatus(task.title)?.equals("Completed", ignoreCase = true) == true
        }
        adapter.updateList(filtered)
    }

    private fun showPending() {
        val filtered = fullList.filter { task ->
            val status = prefs.getStatus(task.title)
            status == null || status.equals("Pending", ignoreCase = true)
        }
        adapter.updateList(filtered)
    }


    override fun onResume() {
        super.onResume()
        viewModel.loadTasks()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewData)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize adapter
        adapter = SampleAdapter(emptyList(), this) { clickedItem ->
            // Before navigation, update status from preferences (if exists)
            val savedStatus = prefs.getStatus(clickedItem.title)
            if (savedStatus != null) {
                clickedItem.status = savedStatus
            }

            // Navigate to second screen
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("title", clickedItem.title)
            intent.putExtra("description", clickedItem.description)
            intent.putExtra("status", clickedItem.status)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }

}


