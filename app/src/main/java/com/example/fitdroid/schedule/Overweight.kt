package com.example.fitdroid.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fitdroid.R
import com.example.fitdroid.databinding.OverweightBinding
import com.example.fitdroid.databinding.UnderweightBinding
import com.example.fitdroid.databinding.WorkoutBinding


class Overweight : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = OverweightBinding.inflate(layoutInflater)
        val args = OverweightArgs.fromBundle(requireArguments())
        binding.BmiText.text ="Your BMI is ${args.bmi}. You are Overweight.\n" +
                "PLease click the button below for your recommended weekly workout schedule"

        binding.Schedule.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_overweight_to_overschedule)
        }
        binding.Home.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_overweight_to_homepage)
        }
        binding.Back.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_overweight_to_workout)
        }
        return binding.root
    }
}
