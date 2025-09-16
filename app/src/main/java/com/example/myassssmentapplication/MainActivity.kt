package com.example.myassssmentapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private val api: ApiService by lazy { RetrofitClient.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin   = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener { attemptLogin() }
    }

    private fun attemptLogin() {
        Toast.makeText(this, "Login tapped", Toast.LENGTH_SHORT).show()

        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and student ID", Toast.LENGTH_SHORT).show()
            return
        }

        val body = LoginRequest(username, password)
        Log.d("LOGIN", "Calling /sydney/auth with $body")

        api.login(body).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("LOGIN", "onResponse code=${response.code()}")
                if (response.isSuccessful) {
                    val keypass = response.body()?.keypass.orEmpty()
                    Toast.makeText(this@MainActivity, "Login OK, keypass=$keypass", Toast.LENGTH_SHORT).show()
                    if (keypass.isBlank()) return
                    startActivity(
                        Intent(this@MainActivity, DashboardActivity::class.java)
                            .putExtra("keypass", keypass)
                    )
                    finish()
                } else {
                    val err = try { response.errorBody()?.string() } catch (_: Exception) { null }
                    Log.e("LOGIN", "HTTP ${response.code()} body=$err")
                    Toast.makeText(
                        this@MainActivity,
                        "Login failed (${response.code()}): ${err ?: "server rejected request"}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LOGIN", "onFailure ${t.localizedMessage}", t)
                Toast.makeText(this@MainActivity, "Network error: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
