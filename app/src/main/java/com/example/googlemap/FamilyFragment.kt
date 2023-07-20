package com.example.googlemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.fragment.app.commit
import com.example.googlemap.databinding.FamilyFragmentBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class FamilyFragment : Fragment() {

    private lateinit var binding: FamilyFragmentBinding
    private lateinit var map: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var puneMarker: Marker

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.family_fragment, null)
        binding = FamilyFragmentBinding.bind(view)
        mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync {
            map = it

            setUpListener()
        }
        return view
    }

    private fun setUpListener() {
        var markerOptionPune = MarkerOptions()
        markerOptionPune.position(LatLng(18.5204, 73.8567)).title("Pune").snippet("Our Pune")
            .draggable(true)

        puneMarker = map.addMarker(markerOptionPune)!!

        binding.btnGo.setOnClickListener(BtnGoClickListener())

        binding.btnSearchByDate.setOnClickListener {

            val inflater = LayoutInflater.from(requireContext())
            val linearLayout = inflater.inflate(R.layout.container_search_by_date, binding.searchByDateContainer,
                false) as LinearLayout

            binding.searchByDateContainer.addView(linearLayout)

                    }

    }
    private inner class BtnGoClickListener : View.OnClickListener{

        override fun onClick(view: View?) {
            if (binding.btnLiveTracking.isChecked) {

                val liveTrackingFragment = LiveTrackingFragment()
                parentFragmentManager.commit {
                    replace(R.id.familyFragment, liveTrackingFragment, "familyfragment")
                    addToBackStack(null)
                }
            }


            else if(binding.btnSearchByDate.isChecked){

                val fragment = SortByDateFragment()
                val fragmentManager = childFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.familyFragment, fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }


        }
        }

            }







