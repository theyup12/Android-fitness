package com.example.fitdroid.user_scale_data

import android.app.Application
import android.icu.number.Scale
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.fitdroid.database.ScaleDao
import com.example.fitdroid.database.User_Scale
import kotlinx.coroutines.launch

class UserScaleViewModel(
    val database: ScaleDao,
    application: Application) : AndroidViewModel(application) {

    var tall = MutableLiveData("")
    var weight = MutableLiveData("")
    var goal = MutableLiveData("")

    val scaleList = database.getAllScale()

    var scaleString = Transformations.map(scaleList){
            scales ->
        var result = ""
        for(user in scales){
            result += "${user.tall} @ ${user.weight}\n"
        }
        result
    }
    fun insert(){
        viewModelScope.launch {
            var scale = User_Scale()
            scale.tall = tall.value.toString()
            scale.weight = weight.value.toString()
            scale.goal = goal.value.toString()

            database.insert(scale)
        }
    }
    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }
}