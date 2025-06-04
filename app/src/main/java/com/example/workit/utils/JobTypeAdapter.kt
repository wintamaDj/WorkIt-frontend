package com.example.workit.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.workit.R
import com.example.workit.model.JobType

class JobTypeAdapter(private val jobTypes: List<JobType>) : RecyclerView.Adapter<JobTypeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jobTypeText: TextView = itemView.findViewById(R.id.tv_job_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.job_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jobType = jobTypes[position]
        holder.jobTypeText.text = jobType.typeName

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            Toast.makeText(context, "Viewing ${jobType.typeName}", Toast.LENGTH_SHORT).show()

            // val intent = Intent(context, HomePage::class.java).apply {
            //     putExtra("filter_category", jobType.typeName)
            //     putExtra("switch_to_jobs", true)
            // }
            // context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = jobTypes.size
}