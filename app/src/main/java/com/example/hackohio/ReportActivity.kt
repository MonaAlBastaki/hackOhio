package com.example.hackohio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.Button

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportpage) // Set the XML layout

        // Find buttons by ID
        val buttonUnevenSidewalk = findViewById<Button>(R.id.buttonUneven)
        val buttonSidewalkMissing = findViewById<Button>(R.id.buttonMissing)
        val buttonPoorlyLit = findViewById<Button>(R.id.buttonPoorly)
        val buttonInaudibleSignal = findViewById<Button>(R.id.buttonSignal)
        val buttonNoCurbCut = findViewById<Button>(R.id.buttonCurb)
        val buttonNoDomeMat = findViewById<Button>(R.id.buttonDome)
        val buttonExit = findViewById<Button>(R.id.buttonExit)

        // Set onClick listeners
        buttonUnevenSidewalk.setOnClickListener {

        }

        buttonSidewalkMissing.setOnClickListener {
            // Handle the action for the "Sidewalk Missing" button
            Toast.makeText(this, "Sidewalk Missing Reported", Toast.LENGTH_SHORT).show()
        }

        buttonPoorlyLit.setOnClickListener {
            // Handle the action for the "Poorly Lit" button
            Toast.makeText(this, "Poorly Lit Area Reported", Toast.LENGTH_SHORT).show()
        }

        buttonInaudibleSignal.setOnClickListener {
            // Handle the action for the "Inaudible Traffic Signal" button
            Toast.makeText(this, "Inaudible Traffic Signal Reported", Toast.LENGTH_SHORT).show()
        }

        buttonNoCurbCut.setOnClickListener {
            // Handle the action for the "No Curb Cut" button
            Toast.makeText(this, "No Curb Cut Reported", Toast.LENGTH_SHORT).show()
        }

        buttonNoDomeMat.setOnClickListener {
            // Handle the action for the "No Dome Mat" button
            Toast.makeText(this, "No Dome Mat Reported", Toast.LENGTH_SHORT).show()
        }

        buttonExit.setOnClickListener {
            //exit to the main menu
            finish()
        }
    }

}
