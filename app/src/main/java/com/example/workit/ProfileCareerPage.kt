package com.example.workit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ProfileCareerPage : AppCompatActivity() {

    private lateinit var etJobTitle: EditText
    private lateinit var etCompanyName: EditText
    private lateinit var etStartDate: EditText
    private lateinit var etEndDate: EditText
    private lateinit var etDescription: EditText
    private lateinit var switchCurrent: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_career_page)

        initViews()
        setupButtons()
        setupSwitch()
    }

    private fun initViews() {
        etJobTitle = findViewById(R.id.et_job_title)
        etCompanyName = findViewById(R.id.et_company_name)
        etStartDate = findViewById(R.id.et_start_date)
        etEndDate = findViewById(R.id.et_end_date)
        etDescription = findViewById(R.id.et_description)
        switchCurrent = findViewById(R.id.switch_current)
    }

    private fun setupButtons() {
        val backButton = findViewById<ImageButton>(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }

        val addCareerButton = findViewById<Button>(R.id.btn_apply_job)
        addCareerButton.setOnClickListener {
            handleAddCareer()
        }
    }

    private fun setupSwitch() {
        switchCurrent.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                etEndDate.isEnabled = false
                etEndDate.text.clear()
                etEndDate.hint = "Current"
                etEndDate.alpha = 0.5f
            } else {
                etEndDate.isEnabled = true
                etEndDate.hint = "Enter Month/Year"
                etEndDate.alpha = 1.0f
            }
        }
    }

    private fun handleAddCareer() {
        val jobTitle = etJobTitle.text.toString().trim()
        val companyName = etCompanyName.text.toString().trim()
        val startDate = etStartDate.text.toString().trim()
        val endDate = if (switchCurrent.isChecked) "Current" else etEndDate.text.toString().trim()
        val description = etDescription.text.toString().trim()

        if (jobTitle.isEmpty()) {
            etJobTitle.error = "Job title is required"
            etJobTitle.requestFocus()
            return
        }

        if (companyName.isEmpty()) {
            etCompanyName.error = "Company name is required"
            etCompanyName.requestFocus()
            return
        }

        if (!switchCurrent.isChecked && endDate.isEmpty()) {
            etEndDate.error = "End date is required"
            etEndDate.requestFocus()
            return
        }

        val message = if (switchCurrent.isChecked) {
            "Current career position added successfully!"
        } else {
            "Career experience added successfully!"
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        clearForm()

        finish()
    }

    private fun clearForm() {
        etJobTitle.text.clear()
        etCompanyName.text.clear()
        etStartDate.text.clear()
        etEndDate.text.clear()
        etDescription.text.clear()
        switchCurrent.isChecked = false

        etEndDate.isEnabled = true
        etEndDate.hint = "Enter Month/Year"
        etEndDate.alpha = 1.0f
    }
}