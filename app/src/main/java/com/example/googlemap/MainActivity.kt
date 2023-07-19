 package com.example.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

 class MainActivity : AppCompatActivity() {

    private lateinit var map: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var puneMarker : Marker




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapFragment= supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync {
            map=it

            setUpListener()


        }

    }

     private fun setUpListener(){
         var markerOptionPune = MarkerOptions()
         markerOptionPune.position(LatLng(18.5204, 73.8567)).title("Pune").snippet("Our Pune").draggable(true)

         puneMarker  =  map.addMarker(markerOptionPune )!!

     }
}