package com.example.googlemap

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.googlemap.databinding.FamilyFragmentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.libraries.mapsplatform.transportation.consumer.model.Route
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.maps.DirectionsApi
import com.google.maps.DirectionsApiRequest
import com.google.maps.GeoApiContext
import com.google.maps.model.DirectionsResult
import com.google.maps.model.TravelMode





class FamilyFragment : Fragment() {

    private lateinit var binding: FamilyFragmentBinding
    private lateinit var map: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var puneMarker: Marker
    private lateinit var geoApiContext: GeoApiContext

    private lateinit var editTextDate: TextInputEditText
    private lateinit var textFromTime: TextInputEditText
    private lateinit var textToTime: TextInputEditText
    private lateinit var textInputLayout: TextInputLayout
    private val startLatLng : LatLng?= LatLng(18.5204, 73.8567)
    private val endLatLng : LatLng?= LatLng(18.5018, 73.8636)




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

        geoApiContext = createGeoApiContext("http://premisafe.com/production/individual_app/smart_wrist/watch_gps.php")

        return view
    }

    private fun setUpListener() {
        val markerOptionPune = MarkerOptions()
        markerOptionPune.position(LatLng(18.5204, 73.8567)).title("Pune").snippet("Our Pune")
            .draggable(true)

        puneMarker = map.addMarker(markerOptionPune)!!

        binding.btnGo.setOnClickListener(BtnGoClickListener())

        binding.btnSearchByDate.setOnClickListener {

            btnSearchByDate()

        }
    }

    private fun btnSearchByDate() {

        val inflater = LayoutInflater.from(requireContext())
        val linearLayout = inflater.inflate(
            R.layout.container_search_by_date, binding.searchByDateContainer,
            false
        ) as LinearLayout

        binding.searchByDateContainer.addView(linearLayout)



//        editTextDate.setOnClickListener {
//            val datePickerDialog = DatePickerDialog(
//                requireContext(),
//                MyOnDateSetListener(),
//                2023,
//                2,
//                25
//            )
//
//            datePickerDialog.show()
//
//        }


    }

    private inner class BtnGoClickListener : View.OnClickListener {
        override fun onClick(view: View?) {
            if (binding.btnLiveTracking.isChecked) {

                val fragment = LiveTrackingFragment()
                val fragmentManager = childFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.familyFragment, fragment)
                fragmentTransaction.addToBackStack(FamilyFragment().toString())
                fragmentTransaction.commit()
            } else if (binding.btnSearchByDate.isChecked) {

                val fragment = SortByDateFragment()
                val fragmentManager = childFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.familyFragment, fragment)
                fragmentTransaction.addToBackStack(FamilyFragment().toString())
                fragmentTransaction.commit()

                drawRoute(
                    startLatLng!!, // Example start LatLng (Pune)
                   endLatLng!! // Example end LatLng (Your destination)
                )
            }


        }


    }



    private fun createGeoApiContext(apiKey: String): GeoApiContext {
        return GeoApiContext.Builder()
            .apiKey(apiKey)
            .build()
   }
    private fun drawRoute(startLatLng: LatLng, endLatLng: LatLng) {
        map.addMarker(MarkerOptions().position(startLatLng!!).title("Start"))
        map.addMarker(MarkerOptions().position(endLatLng!!).title("End"))

        val polylineOptions = PolylineOptions()
            .add(startLatLng)
            .add(endLatLng)
            .width(5f)
            .color(Color.BLUE) // Customize the polyline color as needed
        map.addPolyline(polylineOptions)


        //move camera on root
        val builder = LatLngBounds.builder()
        builder.include(startLatLng!!)
        builder.include(endLatLng!!)
        val bounds = builder.build()
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }







}







//    private fun drawRoute(startLatLng: LatLng, endLatLng: LatLng) {
//        val request = DirectionsApi.newRequest(geoApiContext)
//            .origin(com.google.maps.model.LatLng(startLatLng.latitude, startLatLng.longitude))
//            .destination(com.google.maps.model.LatLng(endLatLng.latitude, endLatLng.longitude))
//            .mode(TravelMode.DRIVING)
//
//        try {
//            val result: DirectionsResult = request.await()
//            if (result.routes.isNotEmpty()) {
//                val route = result.routes[0]
//                val polylineOptions = PolylineOptions()
//                val legs = route.legs
//                for (i in legs.indices) {
//                    val steps = legs[i].steps
//                    for (j in steps.indices) {
//                        val points = steps[j].polyline.decodePath()
//                        for (point in points) {
//                            polylineOptions.add(
//                                LatLng(
//                                    point.lat,
//                                    point.lng
//                                )
//                            )
//                        }
//                    }
//                }
//
//                map.addPolyline(polylineOptions)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }












