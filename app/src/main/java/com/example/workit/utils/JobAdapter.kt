package com.example.workit.utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workit.JobDetailPage
import com.example.workit.R
import com.example.workit.model.JobItem

class JobAdapter(private val jobList: MutableList<JobItem> = mutableListOf()) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.company_logo)
        val jobTitleView: TextView = itemView.findViewById(R.id.job_title)
        val companyNameView: TextView = itemView.findViewById(R.id.company_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_job, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = jobList[position]
        holder.jobTitleView.text = item.job_name
        holder.companyNameView.text = item.company_name
        holder.imageView.setImageResource(item.logo)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, JobDetailPage::class.java).apply {
                putExtra("job_name", item.job_name)
                putExtra("company_name", item.company_name)
                putExtra("company_logo", item.logo)
                putExtra("category", item.category)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = jobList.size

    fun updateData(newList: List<JobItem>) {
        val oldList = ArrayList(jobList)
        jobList.clear()
        jobList.addAll(newList)
        notifyDataSetChanged()
    }
}