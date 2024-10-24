package com.example.composeapiconsume.android.features.auth.data.models

import com.example.composeapiconsume.android.features.auth.domain.entities.LoginEntity
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,

    )

fun LoginResponse.mapFromEntity() = LoginEntity(
    token = this.token
)

fun LoginEntity.mapFromDomain() = LoginResponse(
    token = this.token
)

fun List<LoginResponse>.mapFromListModel(): List<LoginEntity> {
    return this.map {
        it.mapFromEntity()
    }
}

fun List<LoginEntity>.mapFromListDomain(): List<LoginResponse> {
    return this.map {
        it.mapFromDomain()
    }
}