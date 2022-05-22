package com.example.fitdroid.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Profile entity
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var UserId: Long = 0L,

    @ColumnInfo()
    var name: String = "",

    @ColumnInfo()
    var email: String = "",

    @ColumnInfo()
    var age: Int = 0,

    @ColumnInfo()
    var address: String = "",

)
@Entity(tableName = "scale_table")
data class User_Scale(
    @PrimaryKey(autoGenerate = true)
    var ScaleId: Long = 0L,

    @ColumnInfo()
    var tall: String = "",

    @ColumnInfo()
    var weight: String = "",

    @ColumnInfo()
    var goal: String = "",

    )