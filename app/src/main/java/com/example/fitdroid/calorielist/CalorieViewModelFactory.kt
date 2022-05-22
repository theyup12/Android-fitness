package com.example.fitdroid.calorielist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitdroid.database.CalorieDao

/**
 * Generates an CalorieViewModel tied to the database.
 */
class CalorieViewModelFactory(
    private val dataSource: CalorieDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the CalorieViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalorieViewModel::class.java)) { // ViewModel class
            return CalorieViewModel(dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}