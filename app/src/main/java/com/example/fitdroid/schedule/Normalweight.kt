package com.example.fitdroid.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fitdroid.R
import com.example.fitdroid.databinding.NormalweightBinding
import com.example.fitdroid.databinding.UnderweightBinding
import com.example.fitdroid.databinding.WorkoutBinding


class Normalweight : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = NormalweightBinding.inflate(layoutInflater)
        val args = NormalweightArgs.fromBundle(requireArguments())
        binding.BmiText.text ="Your BMI is ${args.bmi}. You are in good shape.\n" +
                "Please click the button below for the recommend weekly workout schedule: "

        binding.Schedule.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_normalweight_to_normalschedule)
        }
        binding.Home.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_normalweight_to_homepage)
        }
        binding.Back.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_normalweight_to_workout)
        }
        return binding.root
    }
}