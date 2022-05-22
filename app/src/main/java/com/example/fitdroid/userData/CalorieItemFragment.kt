package com.example.fitdroid.userData

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitdroid.R
import com.example.fitdroid.database.CalorieDatabase
import com.example.fitdroid.databinding.CalorieItemFragmentBinding
import com.example.fitdroid.calorielist.CalorieViewModel
import com.example.fitdroid.calorielist.CalorieViewModelFactory

/**
 * Fragment that displays a single calorie.
 */
class CalorieItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve arguments passed from the RecyclerView
        val args = CalorieItemFragmentArgs.fromBundle(
            requireArguments()
        )

        // Create data binding
        val binding: CalorieItemFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.calorie_item_fragment, container, false)

        // Get reference to this application
        val application = requireNotNull(this.activity).application

        // Retrieve Calorie data access object.
        val dataSource = CalorieDatabase.getInstance(application).calorieDao

        // Create a factory that generates an CalorieViewModel connected to the database. The
        // calorieId passed from the RecyclerView is used to display the corresponding
        // information.
        val viewModelFactory =
            CalorieItemViewModelFactory(args.calorieId, dataSource, application)

        // Generate an CalorieViewModel using the factory.
        val calorieItemViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(CalorieItemViewModel::class.java)

        // Connect the CalorieViewModel with the variable in the layout
        binding.calorieItemViewModel = calorieItemViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        return binding.root
    }
}