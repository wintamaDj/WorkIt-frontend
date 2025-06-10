package com.example.workit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.workit.utils.DB

class LoginPage : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var etName: EditText
    lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)

//        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnGoogle = findViewById<Button>(R.id.btn_google)
        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val tvRegister = findViewById<TextView>(R.id.tv_reg)
        val tvOr = findViewById<TextView>(R.id.tv_or)

        tvRegister.setOnClickListener {
            val i = Intent(this@LoginPage, RegisterPage::class.java)
            startActivity(i)
        }

        btnLogin.setOnClickListener {
            signIn()
        }
    }

    fun signIn() {

        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        DB.syncData(this)
        DB.login(email, password)

        if (DB.LOGGED_IN_USER == null) {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, HomePage::class.java))
        finish()

    }
}