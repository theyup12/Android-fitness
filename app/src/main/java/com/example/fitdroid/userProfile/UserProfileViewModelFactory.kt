package com.example.fitdroid.userProfile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitdroid.database.ScaleDao
import com.example.fitdroid.database.UserDao
import com.example.fitdroid.userData.UserViewModel
import java.lang.IllegalArgumentException

class UserProfileViewModelFactory (
    private val dataSource: UserDao,
    private val dataSource2: ScaleDao,
    private val application: Application
): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            return UserProfileViewModel(dataSource, dataSource2, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}