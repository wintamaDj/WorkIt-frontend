package com.example.workit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class JobDetailPage : AppCompatActivity() {

    private var currentCompanyName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_job_detail_page)

        val jobName = intent.getStringExtra("job_name") ?: "Job Title"
        val companyName = intent.getStringExtra("company_name") ?: "Company Name"
        val companyLogo = intent.getIntExtra("company_logo", R.drawable.starbucks_logo)
        val category = intent.getStringExtra("category") ?: "Category"

        currentCompanyName = companyName

        setupButtons()
        updateJobData(jobName, companyName, companyLogo, category)
        setupReviewSection()
    }

    private fun setupButtons() {
        try {
            val backButton = findViewById<ImageButton>(R.id.btn_back)
            backButton.setOnClickListener {
                finish()
            }

            val notificationButton = findViewById<ImageButton>(R.id.btn_notif)
            notificationButton.setOnClickListener {
                Toast.makeText(this, "Notifications clicked!", Toast.LENGTH_SHORT).show()
                // val intent = Intent(this, NotificationPage::class.java)
                // startActivity(intent)
            }

            val applyButton = findViewById<Button>(R.id.btn_apply_job)
            applyButton.setOnClickListener {
                val jobName = intent.getStringExtra("job_name") ?: "this job"
                Toast.makeText(this, "Applied for $jobName successfully!", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupReviewSection() {
        try {
            val reviewSection = findViewById<LinearLayout>(R.id.ll_review_section)
            reviewSection.setOnClickListener {
                val intent = Intent(this, ReviewPage::class.java)
                intent.putExtra("company_name", currentCompanyName)
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error setting up review section", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateJobData(jobName: String, companyName: String, companyLogo: Int, category: String) {
        try {
            val ivCompanyLogo = findViewById<ImageView>(R.id.iv_logo)
            ivCompanyLogo.setImageResource(companyLogo)

            val tvCompanyName = findViewById<TextView>(R.id.tv_company_name)
            tvCompanyName.text = companyName

            val tvJobName = findViewById<TextView>(R.id.tv_job_type)
            tvJobName.text = jobName

        } catch (e: Exception) {
            Toast.makeText(this, "Viewing: $jobName at $companyName", Toast.LENGTH_LONG).show()
        }
    }
}