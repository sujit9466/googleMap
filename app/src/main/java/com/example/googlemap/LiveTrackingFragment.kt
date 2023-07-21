package com.example.googlemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.googlemap.databinding.LiveTrackingFragmentBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

class LiveTrackingFragment : Fragment() {
    private lateinit var binding : LiveTrackingFragmentBinding
    private lateinit var mapFragment : SupportMapFragment
    private lateinit var map: GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.live_tracking_fragment, null)
        binding = LiveTrackingFragmentBinding.bind(view)
        mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync {
           map = it

            setUpListener()
        }
        return view

    }


    private fun setUpListener(){

    }

}