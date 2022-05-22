package com.example.fitdroid.calorielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.fitdroid.R
import com.example.fitdroid.database.CalorieDatabase
import com.example.fitdroid.databinding.CalorieListFragmentBinding

/**
 * Fragment that displays the input text fields and calorie list
 */
class CalorieListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create data binding
        val binding: CalorieListFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.calorie_list_fragment, container, false)

        // Get reference to the application
        val application = requireNotNull(this.activity).application

        // Retrieve Calorie data access object.
        val dataSource = CalorieDatabase.getInstance(application).calorieDao

        // Create a factory that generates CalorieViewModels connected to the database.
        val viewModelFactory = CalorieViewModelFactory(dataSource, application)

        // Generate an CalorieViewModel using the factory.
        val calorieViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(CalorieViewModel::class.java)

        // Connect the CalorieViewModel with the variable in the layout
        binding.calorieViewModel = calorieViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        binding.homeButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_food_to_homepage)
        }

        // Provide a lambda function that is called when the RecyclerView item is selected.
        var calorieAdapter = CalorieListAdapter(CalorieListener {
            calorieId ->
            // Navigate to the calorie view and provide the id of the calorie referenced
            // by the select RecyclerView item.
            this.findNavController().navigate(
                CalorieListFragmentDirections
                    .actionCalorieListFragmentToCalorieItemFragment(calorieId)
            )
        })
        // Attach calorie adapter.
        binding.calorieRecyclerview.adapter = calorieAdapter

        // Submit an updated list to the calorie listAdapter whenever it changes. Take note
        // calorieList is a LiveData object.
        calorieViewModel.calorieList.observe(viewLifecycleOwner, Observer {
            it?.let {
                calorieAdapter.submitList(it)
            }
        })
        return binding.root
    }
}