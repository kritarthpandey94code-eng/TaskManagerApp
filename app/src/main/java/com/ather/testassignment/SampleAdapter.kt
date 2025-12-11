package com.ather.testassignment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ather.TaskItem

class SampleAdapter(
    private var items: List<TaskItem>,
    private val context: Context,
    private val onItemClick: (TaskItem) -> Unit
) : RecyclerView.Adapter<SampleAdapter.TaskViewHolder>() {

    private val prefs = SharedPrefHelper(context)

    fun updateList(newItems: List<TaskItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val description: TextView = itemView.findViewById(R.id.tvDescription)
        val status: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = items[position]

        // Get latest status from SharedPreferences, default to Pending
        val savedStatus = prefs.getStatus(item.title) ?: "Pending"

        holder.title.text = item.title
        holder.description.text = item.description
        holder.status.text = savedStatus

        // Color Code
        holder.status.setTextColor(
            if (savedStatus.equals("Completed", ignoreCase = true))
                Color.parseColor("#388E3C") // green
            else
                Color.parseColor("#D32F2F") // red
        )

        // Pass updated status to click listener
        holder.itemView.setOnClickListener {
            onItemClick(item.copy(status = savedStatus))
        }
    }

    override fun getItemCount() = items.size
}
