package com.example.workit

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class SavedJob(
    val jobName: String,
    val companyName: String,
    val companyLogo: Int,
    val category: String
)

class JobDetailPage : AppCompatActivity() {
    private var currentCompanyName: String = ""
    private var isSaved: Boolean = false
    private var isApplied: Boolean = false
    private lateinit var saveButton: ImageButton
    private lateinit var applyButton: Button
    private lateinit var sharedPreferences: SharedPreferences
    private var currentJob: SavedJob? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_job_detail_page)

        sharedPreferences = getSharedPreferences("saved_jobs", MODE_PRIVATE)

        val jobName = intent.getStringExtra("job_name") ?: "Job Title"
        val companyName = intent.getStringExtra("company_name") ?: "Company Name"
        val companyLogo = intent.getIntExtra("company_logo", R.drawable.starbucks_logo)
        val category = intent.getStringExtra("category") ?: "Category"

        currentCompanyName = companyName
        currentJob = SavedJob(jobName, companyName, companyLogo, category)

        setupButtons()
        updateJobData(jobName, companyName, companyLogo, category)
        setupReviewSection()
        checkIfJobIsSaved()
        checkIfJobIsApplied()
    }

    private fun setupButtons() {
        try {
            val backButton = findViewById<ImageButton>(R.id.btn_back)
            backButton.setOnClickListener {
                finish()
            }

            saveButton = findViewById<ImageButton>(R.id.btn_save)
            saveButton.setOnClickListener {
                toggleSaveJob()
            }

            applyButton = findViewById<Button>(R.id.btn_apply_job)
            applyButton.setOnClickListener {
                applyForJob()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun applyForJob() {
        currentJob?.let { job ->
            if (isApplied) {
                Toast.makeText(this, "You have already applied for this job!", Toast.LENGTH_SHORT).show()
                return
            }

            addJobToApplied(job)
            isApplied = true
            updateApplyButtonState()

            val jobName = job.jobName
            Toast.makeText(this, "Applied for $jobName successfully!", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkIfJobIsApplied() {
        currentJob?.let { job ->
            val appliedJobs = getAppliedJobs()
            isApplied = appliedJobs.any {
                it.jobName == job.jobName && it.companyName == job.companyName
            }
            updateApplyButtonState()
        }
    }

    private fun updateApplyButtonState() {
        if (isApplied) {
            applyButton.text = "Applied ✓"
            applyButton.isEnabled = false
            applyButton.alpha = 0.6f
        } else {
            applyButton.text = "Apply Job"
            applyButton.isEnabled = true
            applyButton.alpha = 1.0f
        }
    }

    private fun addJobToApplied(job: SavedJob) {
        val appliedJobs = getAppliedJobs().toMutableList()
        if (!appliedJobs.any { it.jobName == job.jobName && it.companyName == job.companyName }) {
            appliedJobs.add(job)
            saveAppliedJobs(appliedJobs)
        }
    }

    private fun getAppliedJobs(): List<SavedJob> {
        val json = sharedPreferences.getString("applied_jobs_list", "[]")
        val type = object : TypeToken<List<SavedJob>>() {}.type
        return Gson().fromJson(json, type) ?: emptyList()
    }

    private fun saveAppliedJobs(jobs: List<SavedJob>) {
        val json = Gson().toJson(jobs)
        sharedPreferences.edit().putString("applied_jobs_list", json).apply()
    }

    private fun checkIfJobIsSaved() {
        currentJob?.let { job ->
            val savedJobs = getSavedJobs()
            isSaved = savedJobs.any {
                it.jobName == job.jobName && it.companyName == job.companyName
            }
            updateSaveButtonState()
        }
    }

    private fun toggleSaveJob() {
        currentJob?.let { job ->
            if (isSaved) {
                removeJobFromSaved(job)
                isSaved = false
                Toast.makeText(this, "Removed from saved jobs", Toast.LENGTH_SHORT).show()
            } else {
                addJobToSaved(job)
                isSaved = true
                Toast.makeText(this, "Saved to your profile", Toast.LENGTH_SHORT).show()
            }
            updateSaveButtonState()
        }
    }

    private fun updateSaveButtonState() {
        if (isSaved) {
            saveButton.setImageResource(R.drawable.bookmark_fill)
        } else {
            saveButton.setImageResource(R.drawable.bookmark_button)
        }
    }

    private fun addJobToSaved(job: SavedJob) {
        val savedJobs = getSavedJobs().toMutableList()
        savedJobs.add(job)
        saveSavedJobs(savedJobs)
    }

    private fun removeJobFromSaved(job: SavedJob) {
        val savedJobs = getSavedJobs().toMutableList()
        savedJobs.removeAll {
            it.jobName == job.jobName && it.companyName == job.companyName
        }
        saveSavedJobs(savedJobs)
    }

    private fun getSavedJobs(): List<SavedJob> {
        val json = sharedPreferences.getString("saved_jobs_list", "[]")
        val type = object : TypeToken<List<SavedJob>>() {}.type
        return Gson().fromJson(json, type) ?: emptyList()
    }

    private fun saveSavedJobs(jobs: List<SavedJob>) {
        val json = Gson().toJson(jobs)
        sharedPreferences.edit().putString("saved_jobs_list", json).apply()
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