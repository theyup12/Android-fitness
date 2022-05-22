package com.example.fitdroid.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fitdroid.R
import com.example.fitdroid.databinding.NormalscheduleBinding
import com.example.fitdroid.databinding.NormalweightBinding
import com.example.fitdroid.databinding.OverscheduleBinding

class Overschedule : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = OverscheduleBinding.inflate(layoutInflater)

        binding.Back.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_overschedule_to_workout)
        }
        return binding.root
    }
}