package com.example.demo.data.local

import android.content.SharedPreferences
import com.example.demo.data.response.UserHoldingResponse
import com.google.gson.Gson
import javax.inject.Inject
import androidx.core.content.edit

class LocalService @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {

    companion object {
        const val KEY_USER_HOLDING = "user_holding_data"
    }

    fun getUserHolding(): UserHoldingResponse? {
        val json = sharedPreferences.getString(KEY_USER_HOLDING, null)
        return json?.let {
            gson.fromJson(it, UserHoldingResponse::class.java)
        }
    }

    fun saveUserHolding(userHolding: UserHoldingResponse) {
        val json = gson.toJson(userHolding)
        sharedPreferences.edit { putString(KEY_USER_HOLDING, json) }
    }

}