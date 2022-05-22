package com.example.fitdroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.fitdroid.databinding.WorkoutBinding


class Workout : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = WorkoutBinding.inflate(layoutInflater)

        binding.Ok.setOnClickListener{ view: View ->
            var myfeet : Double = binding.Feet.text.toString().toDouble()
            var myinch : Double = binding.Inches.text.toString().toDouble()
            var myheight = myfeet*12 + myinch
            var myweight : Double = binding.Weight.text.toString().toDouble()

            var BMI : Double =  (myweight / ( myheight * myheight)) *703
            if(BMI <= 18.5) {
                view.findNavController().navigate(WorkoutDirections.actionWorkoutToUnderweight(BMI.toString()))
            }
            else if (BMI <= 24.9){
                view.findNavController().navigate(WorkoutDirections.actionWorkoutToNormalweight(BMI.toString()))
            }
            else {
                view.findNavController().navigate(WorkoutDirections.actionWorkoutToOverweight(BMI.toString()))
            }
        }
        binding.Under.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_workout_to_underschedule)
        }
        binding.Overbutton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_workout_to_overschedule)
        }
        binding.Normal.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_workout_to_normalschedule)
        }

        binding.homeButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_workout_to_homepage)
        }
        return binding.root
    }
}