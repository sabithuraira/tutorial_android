package com.dev.myapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(var name: String? = null, var birthday: String? = null): Parcelable