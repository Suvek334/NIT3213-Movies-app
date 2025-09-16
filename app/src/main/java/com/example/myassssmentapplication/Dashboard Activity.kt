package com.example.myassssmentapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private val api: ApiService by lazy { RetrofitClient.instance }

    private lateinit var rv: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var tvCount: TextView
    private lateinit var adapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val keypass = intent.getStringExtra("keypass") ?: ""
        if (keypass.isEmpty()) {
            Toast.makeText(this, "No keypass provided", Toast.LENGTH_SHORT).show()
            finish(); return
        }

        rv = findViewById(R.id.rvEntities)
        progress = findViewById(R.id.progressBar)
        tvCount = findViewById(R.id.tvCount)

        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        adapter = EntityAdapter { e ->
            startActivity(Intent(this, DetailsActivity::class.java).apply {
                putExtra("title", e.title ?: "")
                putExtra("director", e.director ?: "")
                putExtra("genre", e.genre ?: "")
                putExtra("year", e.releaseYear ?: -1)
                putExtra("description", e.description ?: "")
            })
        }
        rv.adapter = adapter

        loadDashboard(keypass)
    }

    private fun loadDashboard(keypass: String) {
        showLoading(true)
        api.getDashboard(keypass).enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                showLoading(false)
                if (!response.isSuccessful) {
                    Toast.makeText(this@DashboardActivity, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                    return
                }
                val body = response.body() ?: run {
                    Toast.makeText(this@DashboardActivity, "Empty response", Toast.LENGTH_SHORT).show()
                    return
                }
                tvCount.text = "Total: ${body.entityTotal} (items=${body.entities.size})"
                adapter.submitList(body.entities)
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                showLoading(false)
                Toast.makeText(this@DashboardActivity, "Network: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showLoading(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
        rv.visibility = if (show) View.GONE else View.VISIBLE
        tvCount.visibility = if (show) View.GONE else View.VISIBLE
    }
}

