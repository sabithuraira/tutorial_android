package com.dev.myapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SqlTodo(
    var id: Int=0,
    var title: String? = null,
    var description: String? = null): Parcelable