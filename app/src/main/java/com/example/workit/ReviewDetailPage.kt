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
import com.example.workit.model.Review
import com.example.workit.utils.ReviewAdapter

class ReviewDetailPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review_detail_page)


        val reviewerName = intent.getStringExtra("reviewer_name") ?: "Anonymous"
        val jobTitle = intent.getStringExtra("job_title") ?: "Employee"
        val location = intent.getStringExtra("location") ?: "Unknown"
        val rating = intent.getFloatExtra("rating", 5.0f)
        val date = intent.getStringExtra("date") ?: "01/01/2024"
        val reviewText = intent.getStringExtra("review_text") ?: "No review text"
        val benefitsRating = intent.getFloatExtra("benefits_rating", 5.0f)
        val worklifeRating = intent.getFloatExtra("worklife_rating", 5.0f)
        val environmentRating = intent.getFloatExtra("environment_rating", 5.0f)
        val careerRating = intent.getFloatExtra("career_rating", 5.0f)

        setupButtons()
        populateReviewData(reviewerName, jobTitle, location, rating, date, reviewText,
            benefitsRating, worklifeRating, environmentRating, careerRating)
    }

    private fun setupButtons() {
        val closeButton = findViewById<ImageButton>(R.id.btn_close)
        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun populateReviewData(reviewerName: String, jobTitle: String, location: String,
                                   rating: Float, date: String, reviewText: String,
                                   benefitsRating: Float, worklifeRating: Float,
                                   environmentRating: Float, careerRating: Float) {
        try {
            findViewById<TextView>(R.id.tv_reviewer_name).text = reviewerName
            findViewById<TextView>(R.id.tv_job_title).text = jobTitle
            findViewById<TextView>(R.id.tv_location).text = location
            findViewById<TextView>(R.id.tv_date).text = date
            findViewById<TextView>(R.id.tv_review_text).text = reviewText

            setOverallStarRating(rating)

            findViewById<TextView>(R.id.tv_benefits_rating).text = String.format("%.1f", benefitsRating)
            findViewById<TextView>(R.id.tv_worklife_rating).text = String.format("%.1f", worklifeRating)
            findViewById<TextView>(R.id.tv_environment_rating).text = String.format("%.1f", environmentRating)
            findViewById<TextView>(R.id.tv_career_rating).text = String.format("%.1f", careerRating)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setOverallStarRating(rating: Float) {
        try {
            val stars = listOf(
                findViewById<ImageView>(R.id.iv_star1),
                findViewById<ImageView>(R.id.iv_star2),
                findViewById<ImageView>(R.id.iv_star3),
                findViewById<ImageView>(R.id.iv_star4),
                findViewById<ImageView>(R.id.iv_star5)
            )

            for (i in stars.indices) {
                if (i < rating.toInt()) {
                    stars[i].setImageResource(R.drawable.star)
                } else {
                    stars[i].setImageResource(R.drawable.star)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}