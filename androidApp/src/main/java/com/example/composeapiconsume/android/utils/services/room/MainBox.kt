package com.example.composeapiconsume.android.utils.services.room

import android.content.Context
import com.example.composeapiconsume.android.utils.services.room.db.AppDatabase
import com.example.composeapiconsume.android.utils.services.room.db.UserLoginDao
import com.example.composeapiconsume.android.utils.services.room.entity.UserLoginEntity

class MainBoxMixin(private val context: Context) {
    private val userLoginDao: UserLoginDao = AppDatabase.getDatabase(context).userLoginDao()

    // Add data to the database
    suspend fun addData(user: UserLoginEntity) {
        userLoginDao.insertUser(user)
    }

    // Remove data from the database
    suspend fun removeData() {
        userLoginDao.deleteUser()
    }

    // Retrieve data from the database
    suspend fun getData(): UserLoginEntity? {
        return userLoginDao.getUser()
    }

    // Logout and clear user data
    suspend fun logoutBox() {
        removeData() // Clear user data
    }
}
