package com.example.workit

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workit.model.Review
import com.example.workit.utils.ReviewAdapter

class ReviewPage : AppCompatActivity() {

    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var rvReviews: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review_page)

        val companyName = intent.getStringExtra("company_name") ?: "Company"
        val companyLogo = intent.getIntExtra("company_logo", R.drawable.starbucks_logo)

        setupButtons()
        setupReviewsRecyclerView(companyName)
    }

    private fun setupButtons() {
        val backButton = findViewById<ImageButton>(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupReviewsRecyclerView(companyName: String) {
        try {
            rvReviews = findViewById(R.id.rv_reviews)
            rvReviews.layoutManager = LinearLayoutManager(this)

            val reviews = getReviewsForCompany(companyName)
            reviewAdapter = ReviewAdapter(reviews)
            rvReviews.adapter = reviewAdapter

        } catch (e: Exception) {
            Toast.makeText(this, "Add android:id=\"@+id/rv_reviews\" to your RecyclerView", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun getReviewsForCompany(companyName: String): List<Review> {
        return when {
            companyName.contains("Starbucks", ignoreCase = true) -> listOf(
                Review(
                    reviewerName = "Delbert Melvin Setioso",
                    jobTitle = "Barista",
                    location = "Bekasi",
                    rating = 5.0f,
                    date = "01/02/1000",
                    reviewText = "Bagus, saya diperlakukan dengan baik. Working environment is great and the benefits are excellent. The management team is very supportive and there are lots of opportunities for career growth.",
                    benefitsRating = 5.0f,
                    workLifeRating = 5.0f,
                    environmentRating = 5.0f,
                    careerRating = 5.0f
                ),
                Review(
                    reviewerName = "Sarah Johnson",
                    jobTitle = "Shift Supervisor",
                    location = "Jakarta",
                    rating = 4.0f,
                    date = "15/01/2024",
                    reviewText = "Great place to work with amazing team members. Management is supportive and there are good opportunities for growth. The training programs are comprehensive and help develop new skills.",
                    benefitsRating = 4.5f,
                    workLifeRating = 4.0f,
                    environmentRating = 4.5f,
                    careerRating = 4.0f
                ),
                Review(
                    reviewerName = "Muhammad Rizki",
                    jobTitle = "Store Manager",
                    location = "Bandung",
                    rating = 5.0f,
                    date = "28/12/2023",
                    reviewText = "Excellent company culture and great career development opportunities. The training programs are comprehensive and the work-life balance is fantastic. Highly recommend this company!",
                    benefitsRating = 5.0f,
                    workLifeRating = 4.5f,
                    environmentRating = 5.0f,
                    careerRating = 5.0f
                ),
                Review(
                    reviewerName = "Ana Putri",
                    jobTitle = "Barista",
                    location = "Surabaya",
                    rating = 4.5f,
                    date = "10/12/2023",
                    reviewText = "Really enjoy working here. The team is like family and customers are generally nice. Good benefits and flexible scheduling make this a great job for students.",
                    benefitsRating = 4.0f,
                    workLifeRating = 4.5f,
                    environmentRating = 4.5f,
                    careerRating = 4.0f
                )
            )
            companyName.contains("BCA", ignoreCase = true) -> listOf(
                Review(
                    reviewerName = "Lisa Chen",
                    jobTitle = "Customer Service",
                    location = "Jakarta",
                    rating = 4.5f,
                    date = "10/01/2024",
                    reviewText = "Professional work environment with good career prospects. The training is excellent and colleagues are helpful. Great benefits package and job security.",
                    benefitsRating = 4.5f,
                    workLifeRating = 4.0f,
                    environmentRating = 4.5f,
                    careerRating = 4.5f
                ),
                Review(
                    reviewerName = "Ahmad Pratama",
                    jobTitle = "Financial Analyst",
                    location = "Surabaya",
                    rating = 4.0f,
                    date = "05/01/2024",
                    reviewText = "Stable job with good benefits. Work can be challenging but rewarding. Management is supportive and there are clear paths for advancement.",
                    benefitsRating = 4.5f,
                    workLifeRating = 3.5f,
                    environmentRating = 4.0f,
                    careerRating = 4.0f
                ),
                Review(
                    reviewerName = "Dewi Sartika",
                    jobTitle = "Branch Manager",
                    location = "Medan",
                    rating = 4.5f,
                    date = "22/12/2023",
                    reviewText = "Excellent leadership development programs and competitive compensation. Work can be demanding but very rewarding for those who want to grow in banking.",
                    benefitsRating = 4.5f,
                    workLifeRating = 4.0f,
                    environmentRating = 4.0f,
                    careerRating = 5.0f
                )
            )
            companyName.contains("Shopee", ignoreCase = true) -> listOf(
                Review(
                    reviewerName = "Kevin Tan",
                    jobTitle = "Software Engineer",
                    location = "Jakarta",
                    rating = 4.5f,
                    date = "20/01/2024",
                    reviewText = "Dynamic work environment with cutting-edge technology. Fast-paced but exciting. Great for learning and career growth in tech industry.",
                    benefitsRating = 4.5f,
                    workLifeRating = 4.0f,
                    environmentRating = 4.5f,
                    careerRating = 5.0f
                ),
                Review(
                    reviewerName = "Sari Indah",
                    jobTitle = "Marketing Specialist",
                    location = "Jakarta",
                    rating = 4.0f,
                    date = "12/01/2024",
                    reviewText = "Creative and innovative workplace. Lots of opportunities to grow and learn new skills. The team collaboration is excellent and projects are always interesting.",
                    benefitsRating = 4.0f,
                    workLifeRating = 4.0f,
                    environmentRating = 4.5f,
                    careerRating = 4.5f
                )
            )
            else -> listOf(
                Review(
                    reviewerName = "John Doe",
                    jobTitle = "Employee",
                    location = "Jakarta",
                    rating = 4.0f,
                    date = "01/01/2024",
                    reviewText = "Good place to work with decent benefits and supportive management. The work environment is professional and conducive to productivity.",
                    benefitsRating = 4.0f,
                    workLifeRating = 4.0f,
                    environmentRating = 4.0f,
                    careerRating = 4.0f
                ),
                Review(
                    reviewerName = "Maria Santos",
                    jobTitle = "Team Lead",
                    location = "Bandung",
                    rating = 4.5f,
                    date = "15/12/2023",
                    reviewText = "Excellent company with strong values and good leadership. Work-life balance is respected and there are many opportunities for professional development.",
                    benefitsRating = 4.5f,
                    workLifeRating = 4.5f,
                    environmentRating = 4.0f,
                    careerRating = 4.5f
                )
            )
        }
    }
}