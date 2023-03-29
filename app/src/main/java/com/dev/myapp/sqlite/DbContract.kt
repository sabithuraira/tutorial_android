package com.dev.myapp.sqlite

import android.provider.BaseColumns

internal class DbContract {
    internal class TodoTable: BaseColumns{
        companion object{
            const val TABLE_NAME = "todo"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
        }
    }
}