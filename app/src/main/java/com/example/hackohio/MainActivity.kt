package com.example.hackohio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the XML layout

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_container) as SupportMapFragment
        mapFragment.getMapAsync(this) // Set the callback for when the map is ready
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Move the camera to a specified location (e.g., Latitude and Longitude for Ohio)
        val ohio = LatLng(40.0, -83.0)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ohio, 10f)) // Zoom level of 10
    }
}
