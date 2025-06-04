package com.example.workit

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workit.model.JobType
import com.example.workit.utils.JobTypeAdapter

class CompanyDetailPage : AppCompatActivity() {

    private lateinit var jobTypeAdapter: JobTypeAdapter
    private lateinit var rvJobTypes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_company_detail_page)

        val companyName = intent.getStringExtra("company_name") ?: "Company Name"
        val industry = intent.getStringExtra("industry") ?: "Industry"
        val companyLogo = intent.getIntExtra("company_logo", R.drawable.round_shape)

        setupButtons()
        updateCompanyData(companyName, industry, companyLogo)
        setupJobTypesRecyclerView(companyName, industry)
    }

    private fun setupButtons() {
        val backButton = findViewById<ImageButton>(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }

        val notificationButton = findViewById<ImageButton>(R.id.btn_notif)
        notificationButton.setOnClickListener {
            Toast.makeText(this, "Notifications clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateCompanyData(companyName: String, industry: String, companyLogo: Int) {
        try {
            val tvCompanyName = findViewById<TextView>(R.id.tv_company_name)
            tvCompanyName.text = companyName

            val ivCompanyLogo = findViewById<ImageView>(R.id.iv_logo)
            ivCompanyLogo.setImageResource(companyLogo)

        } catch (e: Exception) {
            Toast.makeText(this, "Viewing: $companyName ($industry)", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupJobTypesRecyclerView(companyName: String, industry: String) {
        try {
            rvJobTypes = findViewById(R.id.rv_job_types)
            rvJobTypes.layoutManager = LinearLayoutManager(this)

            val jobTypes = getJobTypesForCompany(companyName, industry)

            jobTypeAdapter = JobTypeAdapter(jobTypes)
            rvJobTypes.adapter = jobTypeAdapter

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getJobTypesForCompany(companyName: String, industry: String): List<JobType> {

        return when {
            companyName.contains("Starbucks", ignoreCase = true) -> listOf(
                JobType("Retail Jobs"),
                JobType("Food Service Jobs"),
                JobType("Management Jobs")
            )

            companyName.contains("BCA", ignoreCase = true) || companyName.contains(
                "OCBC",
                ignoreCase = true
            ) -> listOf(
                JobType("Banking Jobs"),
                JobType("Financial Technology Jobs"),
                JobType("Customer Service Jobs"),
                JobType("IT Jobs")
            )

            companyName.contains("Shopee", ignoreCase = true) -> listOf(
                JobType("E-commerce Jobs"),
                JobType("Technology Jobs"),
                JobType("Marketing Jobs"),
                JobType("Operations Jobs")
            )

            industry.contains("Technology", ignoreCase = true) -> listOf(
                JobType("Software Development"),
                JobType("Data Science"),
                JobType("Product Management"),
                JobType("UI/UX Design")
            )

            industry.contains("Financial", ignoreCase = true) -> listOf(
                JobType("Banking Jobs"),
                JobType("Investment Jobs"),
                JobType("Risk Management"),
                JobType("Compliance Jobs")
            )

            industry.contains("E-commerce", ignoreCase = true) -> listOf(
                JobType("Online Marketing"),
                JobType("Supply Chain"),
                JobType("Customer Support"),
                JobType("Business Development")
            )

            else -> listOf(
                JobType("Entry Level Jobs"),
                JobType("Mid Level Jobs"),
                JobType("Senior Level Jobs")
            )
        }
    }
}