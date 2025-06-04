package com.example.workit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileEducationPage : AppCompatActivity() {

    private lateinit var etSchoolName: EditText
    private lateinit var etDegreeLevel: EditText
    private lateinit var etField: EditText
    private lateinit var etStartDate: EditText
    private lateinit var etEndDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_education_page)

        initViews()
        setupButtons()
    }

    private fun initViews() {
        etSchoolName = findViewById(R.id.et_school_name)
        etDegreeLevel = findViewById(R.id.et_degree_level)
        etField = findViewById(R.id.et_field)
        etStartDate = findViewById(R.id.et_start_date)
        etEndDate = findViewById(R.id.et_end_date)
    }

    private fun setupButtons() {
        val backButton = findViewById<ImageButton>(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }

        val addEducationButton = findViewById<Button>(R.id.btn_apply_job)
        addEducationButton.setOnClickListener {
            handleAddEducation()
        }
    }

    private fun handleAddEducation() {
        val schoolName = etSchoolName.text.toString().trim()
        val degreeLevel = etDegreeLevel.text.toString().trim()
        val field = etField.text.toString().trim()
        val startDate = etStartDate.text.toString().trim()
        val endDate = etEndDate.text.toString().trim()

        if (schoolName.isEmpty()) {
            etSchoolName.error = "School name is required"
            etSchoolName.requestFocus()
            return
        }

        if (degreeLevel.isEmpty()) {
            etDegreeLevel.error = "Degree is required"
            etDegreeLevel.requestFocus()
            return
        }

        if (field.isEmpty()) {
            etField.error = "Field of study is required"
            etField.requestFocus()
            return
        }

        Toast.makeText(this, "Education added successfully!", Toast.LENGTH_LONG).show()

        clearForm()

        finish()
    }

    private fun clearForm() {
        etSchoolName.text.clear()
        etDegreeLevel.text.clear()
        etField.text.clear()
        etStartDate.text.clear()
        etEndDate.text.clear()
    }
}