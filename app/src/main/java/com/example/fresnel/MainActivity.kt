package com.example.fresnel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.fresnel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var map: GoogleMap
    private val points = mutableListOf<LatLng>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.btnProfile.setOnClickListener {
            if (points.size == 2) {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("point1_lat", points[0].latitude)
                intent.putExtra("point1_lng", points[0].longitude)
                intent.putExtra("point2_lat", points[1].latitude)
                intent.putExtra("point2_lng", points[1].longitude)
                startActivity(intent)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true

        map.setOnMapClickListener { latLng ->
            if (points.size < 2) {
                points.add(latLng)
                map.addMarker(MarkerOptions().position(latLng))
            }
        }

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(50.0, 30.0), 6f))
    }
}
