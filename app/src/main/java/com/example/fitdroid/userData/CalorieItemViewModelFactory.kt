package com.example.fitdroid.userData
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitdroid.database.CalorieDao
import com.example.fitdroid.calorielist.CalorieViewModel

/**
 * Generates an CalorieViewModel tied to the database. It uses the provided calorie ID to
 * to create the CalorieItemViewModel.
 */
class CalorieItemViewModelFactory(
    private val calorieId: Long,
    private val dataSource: CalorieDao, // Data access object
    private val application: Application
): ViewModelProvider.Factory {

    /**
     * Creates the CalorieViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalorieItemViewModel::class.java)) { // ViewModel class
            return CalorieItemViewModel(calorieId, dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}