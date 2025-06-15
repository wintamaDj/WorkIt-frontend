package com.example.workit

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfilePage : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tvName: TextView
    private lateinit var tvEdit: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_page)

        sharedPreferences = getSharedPreferences("user_profile", MODE_PRIVATE)

        tvName = findViewById(R.id.tv_name)
        tvEdit = findViewById(R.id.tv_edit)

        loadUserName()

        setupEditFunctionality()

        setupBottomNavigation()

        setupProfileButtons()
    }

    private fun loadUserName() {
        val savedName = sharedPreferences.getString("user_name", "[[Name]]")
        tvName.text = savedName
    }

    private fun setupEditFunctionality() {
        tvEdit.setOnClickListener {
            showEditNameDialog()
        }

        tvEdit.setTextColor(getColor(R.color.green))
        tvEdit.isClickable = true
        tvEdit.isFocusable = true
    }

    private fun showEditNameDialog() {
        val dialogView = layoutInflater.inflate(android.R.layout.simple_list_item_1, null)

        val editText = EditText(this).apply {
            setText(tvName.text.toString().replace("[[Name]]", ""))
            hint = "Enter your full name"
            setPadding(50, 40, 50, 40)
            textSize = 16f
        }

        AlertDialog.Builder(this)
            .setTitle("Edit Full Name")
            .setView(editText)
            .setPositiveButton("Save") { dialog, _ ->
                val newName = editText.text.toString().trim()
                if (newName.isNotEmpty()) {
                    saveUserName(newName)
                    tvName.text = newName
                    Toast.makeText(this, "Name updated successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun saveUserName(name: String) {
        sharedPreferences.edit()
            .putString("user_name", name)
            .apply()
    }

    private fun setupBottomNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.selectedItemId = R.id.navigation_profile

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.navigation_saved -> {
                    val intent = Intent(this, SavedPage::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.navigation_discover -> {
                    val intent = Intent(this, CommunityPage::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.navigation_profile -> {
                    true
                }

                else -> false
            }
        }
    }

    private fun setupProfileButtons() {
        try {
            val addRoleButton = findViewById<Button>(R.id.btn_add_role)
            addRoleButton.setOnClickListener {
                val intent = Intent(this, ProfileCareerPage::class.java)
                startActivity(intent)
            }

            val addEducationButton = findViewById<Button>(R.id.btn_add_education)
            addEducationButton.setOnClickListener {
                val intent = Intent(this, ProfileEducationPage::class.java)
                startActivity(intent)
            }

            val addResumeButton = findViewById<Button>(R.id.btn_add_resume)
            addResumeButton.setOnClickListener {
                Toast.makeText(this, "Add Resume feature coming soon!", Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error setting up profile buttons", Toast.LENGTH_SHORT).show()
        }
    }
}