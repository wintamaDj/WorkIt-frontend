package com.example.workit.utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workit.R
import com.example.workit.ReviewDetailPage
import com.example.workit.model.Review

class ReviewAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.iv_profile_pic)
        val reviewerName: TextView = itemView.findViewById(R.id.tv_user_name)
        val jobTitle: TextView = itemView.findViewById(R.id.tv_job_type)
        val reviewText: TextView = itemView.findViewById(R.id.tv_review)
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val star1: ImageView = itemView.findViewById(R.id.iv_star6)
        val star2: ImageView = itemView.findViewById(R.id.iv_star7)
        val star3: ImageView = itemView.findViewById(R.id.iv_star8)
        val star4: ImageView = itemView.findViewById(R.id.iv_star9)
        val star5: ImageView = itemView.findViewById(R.id.iv_star10)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_review, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviews[position]

        holder.reviewerName.text = review.reviewerName

        holder.jobTitle.text = review.jobTitle

        holder.reviewText.text = if (review.reviewText.length > 100) {
            review.reviewText.substring(0, 100) + "..."
        } else {
            review.reviewText
        }

        holder.date.text = review.date

        setStarRating(holder, review.rating)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ReviewDetailPage::class.java).apply {
                putExtra("reviewer_name", review.reviewerName)
                putExtra("job_title", review.jobTitle)
                putExtra("location", review.location)
                putExtra("rating", review.rating)
                putExtra("date", review.date)
                putExtra("review_text", review.reviewText)
                putExtra("benefits_rating", review.benefitsRating)
                putExtra("worklife_rating", review.workLifeRating)
                putExtra("environment_rating", review.environmentRating)
                putExtra("career_rating", review.careerRating)
            }
            context.startActivity(intent)
        }
    }

    private fun setStarRating(holder: ViewHolder, rating: Float) {
        val stars = listOf(holder.star1, holder.star2, holder.star3, holder.star4, holder.star5)

        for (i in stars.indices) {
            if (i < rating.toInt()) {
                stars[i].setImageResource(R.drawable.star)
            } else {
                stars[i].setImageResource(R.drawable.star)
            }
        }
    }

    override fun getItemCount(): Int = reviews.size
}