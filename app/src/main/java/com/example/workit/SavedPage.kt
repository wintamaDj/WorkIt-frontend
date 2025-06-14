package com.example.workit

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SavedPage : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tabLayout: TabLayout
    private lateinit var rvSaved: RecyclerView
    private lateinit var rvApplied: RecyclerView
    private lateinit var savedJobsAdapter: SavedJobsAdapter
    private lateinit var appliedJobsAdapter: AppliedJobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_saved_page)

        sharedPreferences = getSharedPreferences("saved_jobs", MODE_PRIVATE)

        initViews()
        setupRecyclerViews()
        setupBottomNavigation()
        setupTabs()
        showSavedTab()
        loadSavedJobs()
    }

    override fun onResume() {
        super.onResume()
        if (tabLayout.selectedTabPosition == 0) {
            loadSavedJobs()
        }
    }

    private fun initViews() {
        tabLayout = findViewById(R.id.tabs)
        rvSaved = findViewById(R.id.rv_list_saved)
        rvApplied = findViewById(R.id.rv_list_applied)
    }

    private fun setupRecyclerViews() {
        savedJobsAdapter = SavedJobsAdapter { job ->
            openJobDetail(job)
        }
        rvSaved.layoutManager = LinearLayoutManager(this)
        rvSaved.adapter = savedJobsAdapter

        appliedJobsAdapter = AppliedJobsAdapter { job ->
            openJobDetail(job)
        }
        rvApplied.layoutManager = LinearLayoutManager(this)
        rvApplied.adapter = appliedJobsAdapter
    }

    private fun openJobDetail(job: SavedJob) {
        val intent = Intent(this, JobDetailPage::class.java)
        intent.putExtra("job_name", job.jobName)
        intent.putExtra("company_name", job.companyName)
        intent.putExtra("company_logo", job.companyLogo)
        intent.putExtra("category", job.category)
        startActivity(intent)
    }

    private fun setupTabs() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        showSavedTab()
                        loadSavedJobs()
                    }

                    1 -> {
                        showAppliedTab()
                        loadAppliedJobs()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun showSavedTab() {
        rvSaved.visibility = View.VISIBLE
        rvApplied.visibility = View.GONE
    }

    private fun showAppliedTab() {
        rvSaved.visibility = View.GONE
        rvApplied.visibility = View.VISIBLE
    }

    private fun loadSavedJobs() {
        val savedJobs = getSavedJobs()
        savedJobsAdapter.updateJobs(savedJobs)
    }

    private fun loadAppliedJobs() {
        val appliedJobs = getAppliedJobs()
        appliedJobsAdapter.updateJobs(appliedJobs)
    }

    private fun getAppliedJobs(): List<SavedJob> {
        val json = sharedPreferences.getString("applied_jobs_list", "[]")
        val type = object : TypeToken<List<SavedJob>>() {}.type
        return Gson().fromJson(json, type) ?: emptyList()
    }

    private fun getSavedJobs(): List<SavedJob> {
        val json = sharedPreferences.getString("saved_jobs_list", "[]")
        val type = object : TypeToken<List<SavedJob>>() {}.type
        return Gson().fromJson(json, type) ?: emptyList()
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

class SavedJobsAdapter(private val onJobClick: (SavedJob) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var jobs = listOf<SavedJob>()

    companion object {
        private const val VIEW_TYPE_JOB = 0
        private const val VIEW_TYPE_EMPTY = 1
    }

    fun updateJobs(newJobs: List<SavedJob>) {
        jobs = newJobs
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (jobs.isEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_JOB
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> {
                val emptyView = TextView(parent.context).apply {
                    text = "No saved jobs yet"
                    textSize = 16f
                    setTextColor(
                        parent.context.resources.getColor(
                            android.R.color.darker_gray,
                            null
                        )
                    )
                    gravity = android.view.Gravity.CENTER
                    setPadding(0, 100, 0, 100)
                }
                object : RecyclerView.ViewHolder(emptyView) {}
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_saved, parent, false)
                SavedJobViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SavedJobViewHolder && jobs.isNotEmpty()) {
            holder.bind(jobs[position])
        }
    }

    override fun getItemCount() = if (jobs.isEmpty()) 1 else jobs.size

    inner class SavedJobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val companyLogo: ImageView = itemView.findViewById(R.id.company_logo)
        private val jobTitle: TextView = itemView.findViewById(R.id.job_title)
        private val companyName: TextView = itemView.findViewById(R.id.company_name)

        fun bind(job: SavedJob) {
            companyLogo.setImageResource(job.companyLogo)
            jobTitle.text = job.jobName
            companyName.text = job.companyName

            itemView.setOnClickListener {
                onJobClick(job)
            }
        }
    }
}

class AppliedJobsAdapter(private val onJobClick: (SavedJob) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var jobs = listOf<SavedJob>()

    companion object {
        private const val VIEW_TYPE_JOB = 0
        private const val VIEW_TYPE_EMPTY = 1
    }

    fun updateJobs(newJobs: List<SavedJob>) {
        jobs = newJobs
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (jobs.isEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_JOB
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> {
                val emptyView = TextView(parent.context).apply {
                    text = "No applied jobs yet"
                    textSize = 16f
                    setTextColor(
                        parent.context.resources.getColor(
                            android.R.color.darker_gray,
                            null
                        )
                    )
                    gravity = android.view.Gravity.CENTER
                    setPadding(0, 100, 0, 100)
                }
                object : RecyclerView.ViewHolder(emptyView) {}
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_applied, parent, false)
                AppliedJobViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AppliedJobViewHolder && jobs.isNotEmpty()) {
            holder.bind(jobs[position])
        }
    }

    override fun getItemCount() = if (jobs.isEmpty()) 1 else jobs.size

    inner class AppliedJobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val companyLogo: ImageView = itemView.findViewById(R.id.company_logo)
        private val jobTitle: TextView = itemView.findViewById(R.id.job_title)
        private val companyName: TextView = itemView.findViewById(R.id.company_name)

        fun bind(job: SavedJob) {
            companyLogo.setImageResource(job.companyLogo)
            jobTitle.text = job.jobName
            companyName.text = job.companyName

            itemView.setOnClickListener {
                onJobClick(job)
            }
        }
    }
}
