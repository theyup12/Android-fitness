package com.example.fitdroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Data access object for the Calorie entity. The class describes how data is
 * stored, updated, retrieved, or deleted from the database.
 */
@Dao
interface CalorieDao {
    // Add an calorie entity to a table in the database.
    // We use suspend to run the function asynchronously (coroutine).
    @Insert
    suspend fun insert(calorie: Calorie)

    // Update an calorie entity to a table in the database. Often uses the primary key
    // We use suspend to run the function asynchronously (coroutine).
    @Update
    suspend fun update(calorie: Calorie)

    // Custom query for retrieving a single Calorie from a table in the database using
    // its calorie id. We don't use suspend because LiveData objects are already designed
    // to work asynchronous.
    @Query("SELECT * from calorie_table WHERE calorieId = :key")
    fun get(key: Long): LiveData<Calorie>?

    // Custom query for retrieving all Calorie entities from a table in the database.
    // Data is stored to a List LiveData. We don't use suspend because LiveData objects
    // are already designed to work asynchronously.
    @Query("SELECT * from calorie_table ORDER BY calorieId DESC")
    fun getAllCalories(): LiveData<List<Calorie>>

    // Custom query for deleting all entities on a table in the database
    // We use suspend to run the function asynchronously (coroutine).
    @Query("DELETE from calorie_table")
    suspend fun clear()
}