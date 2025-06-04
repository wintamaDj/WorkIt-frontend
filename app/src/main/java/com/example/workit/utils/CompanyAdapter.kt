package com.example.workit.utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workit.CompanyDetailPage
import com.example.workit.R
import com.example.workit.model.CompanyItem

class CompanyAdapter(private val companyList: MutableList<CompanyItem> = mutableListOf()) :
    RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.company_logo)
        val companyNameView: TextView = itemView.findViewById(R.id.company_name)
        val industryView: TextView = itemView.findViewById(R.id.industry_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_company, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = companyList[position]
        holder.companyNameView.text = item.company_name
        holder.industryView.text = item.industry
        holder.imageView.setImageResource(item.logo)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CompanyDetailPage::class.java).apply {
                putExtra("company_name", item.company_name)
                putExtra("industry", item.industry)
                putExtra("company_logo", item.logo)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = companyList.size

    fun updateData(newCompanyList: List<CompanyItem>) {
        companyList.clear()
        companyList.addAll(newCompanyList)
        notifyDataSetChanged()
    }
}