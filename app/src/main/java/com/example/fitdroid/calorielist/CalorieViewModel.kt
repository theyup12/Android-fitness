package com.example.fitdroid.calorielist

import android.app.Application
import androidx.lifecycle.*
import com.example.fitdroid.database.Calorie
import com.example.fitdroid.database.CalorieDao
import kotlinx.coroutines.launch

/**
 * CalorieViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values.
 */
class CalorieViewModel(
    val database: CalorieDao, // Data access object for the Calorie entity
    application: Application
) : AndroidViewModel(application) {

    // Used to assign and retrieve name and location values from the interface.
    var name = MutableLiveData("")
    var location = MutableLiveData("")

    // Retrieves all Calorie objects from the database
    // Represented as a LiveData<List<Calorie>>
    val calorieList = database.getAllCalories()

    /**
     * Inserts the Calorie object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create Calorie object using data stored in the EditText views
            var calorie = Calorie()
            calorie.name = name.value.toString()
            calorie.location = location.value.toString()

            // Insert data to the database using the insert coroutine.
            database.insert(calorie)
        }
    }

    /**
     * Deletes all Calorie entities in the database.
     */
    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }
}