package com.example.composeapiconsume.android.utils.services.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composeapiconsume.android.utils.services.room.entity.UserLoginEntity

@Dao
interface UserLoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserLoginEntity)

    @Query("SELECT * FROM user_login LIMIT 1")
    suspend fun getUser(): UserLoginEntity?

    @Query("DELETE FROM user_login")
    suspend fun deleteUser()
}
