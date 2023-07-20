package com.example.googlemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.googlemap.databinding.FamilyFragmentBinding
import com.example.googlemap.databinding.SortByDateFragmentBinding

class SortByDateFragment : Fragment() {

    private lateinit var binding : SortByDateFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.sort_by_date_fragment, null)

        binding = SortByDateFragmentBinding.bind(view)
        return view

    }
}