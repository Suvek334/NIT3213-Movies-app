package com.example.myassssmentapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvSubtitle = findViewById<TextView>(R.id.tvSubtitle)
        val tvDesc = findViewById<TextView>(R.id.tvDesc)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val title = intent.getStringExtra("title") ?: "Untitled"
        val director = intent.getStringExtra("director").orEmpty()
        val genre = intent.getStringExtra("genre").orEmpty()
        val year = intent.getIntExtra("year", -1)
        val description = intent.getStringExtra("description").orEmpty()

        tvTitle.text = title
        tvSubtitle.text = listOfNotNull(
            director.takeIf { it.isNotBlank() },
            genre.takeIf { it.isNotBlank() },
            if (year > 0) year.toString() else null
        ).joinToString(" • ")
        tvDesc.text = description

        // Handle back button click
        btnBack.setOnClickListener {
            finish()
        }
    }
}
