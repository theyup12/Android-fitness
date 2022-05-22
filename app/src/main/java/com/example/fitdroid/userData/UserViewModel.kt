package com.example.fitdroid.userData

import android.app.Application
import com.example.fitdroid.database.User
import com.example.fitdroid.database.UserDao
import kotlinx.coroutines.launch
import androidx.lifecycle.*

class UserViewModel(
    val database: UserDao,
    application: Application) : AndroidViewModel(application) {
    var name = MutableLiveData("")
    var email = MutableLiveData("")
    var age = MutableLiveData("")
    var address = MutableLiveData("")
    var weight = MutableLiveData("")

    val UserList = database.getAllUser()

    var UserString = Transformations.map(UserList){
            users ->
        var result = ""
        for(user in users){
            result += "${user.name} @ ${user.age}\n"
        }
        result
    }
    fun insert(){
        viewModelScope.launch {
            var user = User()
            user.name = name.value.toString()
            user.email = email.value.toString()
            user.age = Integer.parseInt(age.value.toString())
            user.address = address.value.toString()

            database.insert(user)
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