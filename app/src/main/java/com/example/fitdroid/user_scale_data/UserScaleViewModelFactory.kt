package com.example.fitdroid.user_scale_data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitdroid.database.ScaleDao
import java.lang.IllegalArgumentException

class UserScaleViewModelFactory (
    private val dataSource: ScaleDao,
    private val application: Application): ViewModelProvider.Factory{
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(UserScaleViewModel::class.java)) {
                return UserScaleViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}