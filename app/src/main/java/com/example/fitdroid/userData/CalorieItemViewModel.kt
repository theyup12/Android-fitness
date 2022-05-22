package com.example.fitdroid.userData

import android.app.Application
import androidx.lifecycle.*
import com.example.fitdroid.database.Calorie
import com.example.fitdroid.database.CalorieDao
import kotlinx.coroutines.launch

/**
 * CalorieViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values. It retrieves the corresponding calorie
 * with the provided calorie ID.
 */
class CalorieItemViewModel(
    val calorieId: Long,
    val database: CalorieDao, // Data access object for the Calorie entity
    application: Application
) : AndroidViewModel(application) {

    // Retrieves a LiveData-wrapped calorie object given its ID
    val calorie = database.get(calorieId)
}