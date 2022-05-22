package com.example.fitdroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// User data access object (DAO)
@Dao
interface UserDao{
    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * from user_table WHERE UserId = :key")
    fun get(key: Long): LiveData<User>

    @Query("SELECT * from user_table ORDER BY UserId DESC")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM user_table ORDER BY UserId DESC LIMIT 1")
    fun getLastUser(): LiveData<User>

    @Query("DELETE from user_table")
    suspend fun clear()
}

@Dao
interface ScaleDao{
    @Insert
    suspend fun insert(scale: User_Scale)

    @Update
    suspend fun update(scale: User_Scale)

    @Query("SELECT * from scale_table WHERE ScaleId = :key")
    fun get(key: Long): LiveData<User_Scale>

    @Query("SELECT * from scale_table ORDER BY ScaleId DESC")
    fun getAllScale(): LiveData<List<User_Scale>>

    @Query("SELECT * FROM scale_table ORDER BY ScaleId DESC LIMIT 1")
    fun getLastScale(): LiveData<User_Scale>

    @Query("DELETE from scale_table")
    suspend fun clear()
}