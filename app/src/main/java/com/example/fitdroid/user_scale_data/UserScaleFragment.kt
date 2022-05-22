package com.example.fitdroid.user_scale_data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.fitdroid.R
import com.example.fitdroid.database.UserDatabase
import com.example.fitdroid.databinding.UserScaleFragmentBinding


class UserScaleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: UserScaleFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.user_scale_fragment, container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).scaleDao

        val viewModelFactory = UserScaleViewModelFactory(dataSource, application)

        val userScaleViewModel =
            ViewModelProvider(this, viewModelFactory).get(UserScaleViewModel::class.java)

        binding.userScaleViewModel = userScaleViewModel
        binding.lifecycleOwner = this


        binding.SumbitButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_userScaleFragment_to_userProfileFragment)
        }
        return binding.root
    }
}