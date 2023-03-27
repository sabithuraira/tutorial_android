package com.dev.myapp.data

import android.content.Context
import com.dev.myapp.Profile

internal class ProfileSharePreference(context: Context) {
    companion object{
        private const val PREF_NAME = "profile_preference"

        private const val NAME = "name"
        private const val BIRTHDAY = "birthday"
    }

    private val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setProfile(value: Profile){
        val editor = preference.edit()
        editor.putString(NAME, value.name)
        editor.putString(BIRTHDAY, value.birthday)
        editor.apply()
    }

    fun getProfile(): Profile{
        val data = Profile()
        data.name = preference.getString(NAME, "")
        data.birthday = preference.getString(BIRTHDAY, "")

        return data
    }
}