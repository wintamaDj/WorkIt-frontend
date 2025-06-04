package com.example.workit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workit.model.CommunityPost
import com.example.workit.utils.CommunityAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class CommunityPage : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var communityAdapter: CommunityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_page)

        setupRecyclerView()
        setupBottomNavigation()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_community_posts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        communityAdapter = CommunityAdapter(getCommunityPosts().toMutableList())
        recyclerView.adapter = communityAdapter
    }

    private fun setupBottomNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.selectedItemId = R.id.navigation_discover

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.navigation_discover -> {
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

    private fun getCommunityPosts(): List<CommunityPost> {
        return listOf(
            CommunityPost(
                userName = "Setiawan",
                timePosted = "2hrs ago",
                postContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean tincidunt urna sit amet lacinia egestas."
            ),
            CommunityPost(
                userName = "Budi Yanto",
                timePosted = "2hrs ago",
                postContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            ),
            CommunityPost(
                userName = "Marcella",
                timePosted = "2days ago",
                postContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean tincidunt urna sit amet lacinia egestas. Curabitur in purus sed ante aliquet hendrerit. Sed sed erat eu eros pellentesque interdum."
            ),
            CommunityPost(
                userName = "Budi Yanto",
                timePosted = "2hrs ago",
                postContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            ),
            CommunityPost(
                userName = "Marcella",
                timePosted = "2days ago",
                postContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean tincidunt urna sit amet lacinia egestas."
            )
        )
    }
}