package com.dev.myapp.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.dev.myapp.models.SqlTodo
import com.dev.myapp.sqlite.DbContract.TodoTable.Companion.DESCRIPTION
import com.dev.myapp.sqlite.DbContract.TodoTable.Companion.TABLE_NAME
import com.dev.myapp.sqlite.DbContract.TodoTable.Companion.TITLE
import com.dev.myapp.sqlite.DbContract.TodoTable.Companion._ID

class DbTodo(context: Context) {
    private var dbHelper: DbHelper = DbHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object{
        private const val DATABASE_TABLE = TABLE_NAME

        private var INSTANCE: DbTodo? = null
        fun getInstance(context: Context) : DbTodo = INSTANCE ?: synchronized(this){
                INSTANCE ?: DbTodo(context)
        }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close(){
        dbHelper.close()
        if(database.isOpen){
            database.close()
        }
    }

    fun findAll() : List<SqlTodo>{
        val cursor = database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )

        val listData = mutableListOf<SqlTodo>()
        with(cursor){
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow(_ID))
                val title = getString(getColumnIndexOrThrow(TITLE))
                val description = getString(getColumnIndexOrThrow(DESCRIPTION))
                listData.add(SqlTodo(id, title, description))
            }
            cursor.close()
        }
        return listData
    }

    fun insert(values: ContentValues?): Long{
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int{
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun delete(id: String): Int{
        return database.delete(DATABASE_TABLE,  "$_ID = '$id'", null)
    }

}