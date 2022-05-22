package com.example.fitdroid.userProfile;


import android.app.Application
import android.icu.number.Scale
import androidx.databinding.BindingAdapter
import com.example.fitdroid.database.User
import com.example.fitdroid.database.UserDao
import kotlinx.coroutines.launch
import androidx.lifecycle.*
import com.example.fitdroid.database.ScaleDao

class UserProfileViewModel(
    val database: UserDao,
    val database2: ScaleDao,
    application: Application) : AndroidViewModel(application) {
    val user = database.getLastUser()
    val scale = database2.getLastScale()

    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }
}
