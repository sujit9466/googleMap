package com.example.googlemap

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.DialogInterface
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
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.textfield.TextInputEditText


class FamilyFragment : Fragment() {

    private lateinit var binding: FamilyFragmentBinding
    private lateinit var map: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var puneMarker: Marker
    private lateinit var editTextDate: TextInputEditText
    private lateinit var textFromTime: TextInputEditText
    private lateinit var textToTime: TextInputEditText

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

        editTextDate = linearLayout.findViewById(R.id.edtSelectDate)as TextInputEditText
        textFromTime = linearLayout.findViewById(R.id.textFromTime)as TextInputEditText
        textToTime = linearLayout.findViewById(R.id.textToTime)as TextInputEditText


        editTextDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                MyOnDateSetListener(),
                2023,
                2,
                25
            )

            datePickerDialog.show()

        }


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
            }


        }


    }

    private inner class MyOnDateSetListener : OnDateSetListener {

        override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
            editTextDate.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year)
        }


    }
}










