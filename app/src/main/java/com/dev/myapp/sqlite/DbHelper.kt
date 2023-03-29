package com.dev.myapp.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dev.myapp.sqlite.DbContract.TodoTable.Companion.TABLE_NAME

class DbHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "dbtodo"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE = "CREATE TABLE $TABLE_NAME " +
                "(${DbContract.TodoTable._ID} INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "${DbContract.TodoTable.TITLE} TEXT NOT NULL, " +
                "${DbContract.TodoTable.DESCRIPTION} TEXT NOT NULL)"
    }

    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL(SQL_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }
}