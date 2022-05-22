package com.example.fitdroid.userProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.fitdroid.R;
import com.example.fitdroid.database.UserDatabase;
import com.example.fitdroid.databinding.UserProfileFragmentBinding

class UserProfileFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : UserProfileFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.user_profile_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDao
        val dataSource2 = UserDatabase.getInstance(application).scaleDao
        val viewModelFactory = UserProfileViewModelFactory(dataSource, dataSource2, application)

        val userProfileViewModel =
            ViewModelProvider(this, viewModelFactory).get(UserProfileViewModel::class.java)
        binding.userprofileViewModel = userProfileViewModel

        binding.lifecycleOwner = this

        binding.homeButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_userProfileFragment_to_homepage)
        }
        return binding.root
    }
}