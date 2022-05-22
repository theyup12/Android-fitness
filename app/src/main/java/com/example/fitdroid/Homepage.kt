package com.example.fitdroid

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.fitdroid.databinding.HomepageBinding


class Homepage : Fragment() {
    private var countDownTimer: CountDownTimer? = null
    private var timerDuration: Long = 60000
    private var pauseOffset: Long = 0

    private lateinit var binding: HomepageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = HomepageBinding.inflate(layoutInflater)
        binding.tvTimer.text = "00:${(timerDuration/1000).toString()}"
        binding.btnStart.setOnClickListener{
            startTimer(pauseOffset)
        }
        binding.btnPause.setOnClickListener{
            pauseTimer()
        }
        binding.btnStop.setOnClickListener{
            resetTimer()
        }

        binding.goalsButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_homepage_to_goals)
        }
        binding.calButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_homepage_to_food)
        }

        binding.workoutButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_homepage_to_workout)
        }

        binding.userButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_homepage_to_userModel)
        }
        return binding.root
    }
    private fun startTimer(pauseOffSetL: Long){
        countDownTimer = object : CountDownTimer(
            timerDuration - pauseOffSetL, 1000){
            override fun onTick(millisUntilFinished: Long){
                pauseOffset = timerDuration - millisUntilFinished
                binding.tvTimer.text =
                    "00:${(millisUntilFinished / 1000).toString()}"
            }

            override fun onFinish() {
                binding.tvTimer.text = ("0")
            }
        }.start()
    }
    private fun pauseTimer(){
        if(countDownTimer != null){
            countDownTimer!!.cancel()
        }
    }
    private fun resetTimer(){
        if(countDownTimer != null){
            countDownTimer!!.cancel()
            binding.tvTimer.text = "00:${(timerDuration/1000).toString()}"
            countDownTimer = null
            pauseOffset = 0
        }
    }
}