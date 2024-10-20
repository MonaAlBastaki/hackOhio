package com.example.hackohio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class IssuesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues) // Set the XML layout

        // Find buttons by ID
        val buttonExit = findViewById<Button>(R.id.buttonExit)

        // Set onClick listeners
        buttonExit.setOnClickListener {
            finish()
        }

    }
}
