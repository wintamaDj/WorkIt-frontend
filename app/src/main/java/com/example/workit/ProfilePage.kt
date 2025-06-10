package com.example.workit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_page)

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
        setupProfileButtons()
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