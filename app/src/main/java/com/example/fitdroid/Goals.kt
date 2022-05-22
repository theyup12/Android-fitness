package com.example.fitdroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fitdroid.database.ScaleDao
import com.example.fitdroid.database.UserDatabase
import com.example.fitdroid.databinding.GoalsBinding
import com.example.fitdroid.databinding.HomepageBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class Goals : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = GoalsBinding.inflate(layoutInflater)

        binding.homeButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_goals_to_homepage)
        }

        fun setLineChartData(goalEndDate: Int) {

            val linevalues = ArrayList<Entry>()


            if(goalEndDate > 0) {
                for (i in 1..goalEndDate) {
                    linevalues.add(Entry(i.toFloat(), 250f - i.toFloat()*3))
                }
            } else {
                linevalues.add(Entry(1f, 230F))
                linevalues.add(Entry(2f, 225F))
                linevalues.add(Entry(4f, 220F))
                linevalues.add(Entry(6f, 215F))
                linevalues.add(Entry(8f, 210F))
                linevalues.add(Entry(10f, 205F))
            }

            val linedataset = LineDataSet(linevalues, "Weight")
            //We add features to our chart
            linedataset.color = resources.getColor(R.color.purple_200)
            linedataset.lineWidth = 4f

            linedataset.circleRadius = 10f
            linedataset.setDrawFilled(true)
            linedataset.valueTextSize = 15F
            linedataset.fillColor = resources.getColor(R.color.black)
            linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER)

            //We connect our data to the UI Screen
            val data = LineData(linedataset)
            binding.lineChart.data = data
            binding.lineChart.setBackgroundColor(resources.getColor(R.color.white))
            binding.lineChart.animateXY(1500, 1500, Easing.EaseInCubic)
        }

        binding.goalDateButton.setOnClickListener{ view: View ->
            if (!binding.goalDateText.text.toString().equals("")) {
                var goalEndDate: Int = binding.goalDateText.text.toString().toInt()
                setLineChartData(goalEndDate)
            }
        }

        setLineChartData(0)

        return binding.root
    }


}