package com.example.workit

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class SavedPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_saved_page)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.selectedItemId = R.id.navigation_saved

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.navigation_saved -> {
                    true
                }

                R.id.navigation_discover -> {
                    val intent = Intent(this, CommunityPage::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.navigation_profile -> {
                    val intent = Intent(this, ProfilePage::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                else -> false
            }
        }
    }
}