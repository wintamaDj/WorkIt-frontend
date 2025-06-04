package com.example.workit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.workit.utils.DB

class RegisterPage : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etPassword : EditText
    lateinit var etEmail : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_page)

        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val tvRegister = findViewById<TextView>(R.id.tv_register)
        val btnRegister = findViewById<Button>(R.id.btn_signup)
        etName = findViewById(R.id.et_name)
        etPassword = findViewById(R.id.et_password)
        etEmail = findViewById(R.id.et_email)

        tvRegister.setOnClickListener {
            val i = Intent(this@RegisterPage, LoginPage::class.java)
            startActivity(i)
        }

        btnRegister.setOnClickListener {
            signUp()
        }

    }

    fun signUp() {
        val username = etName.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        // val memberType = TODO: retrieve item from spinner
//            val memberType = "Employee"

        DB.InsertNewUser(
            this@RegisterPage,
            username,
            email,
            password
        )



        finish()
    }
}