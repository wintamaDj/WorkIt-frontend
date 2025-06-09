package com.example.workit.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Helper(
    context: Context?,
) : SQLiteOpenHelper(context, "myDB", null, 2) {

    var SQL_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS user(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT," +
            "email TEXT," +
            "password TEXT," +
            "member_type TEXT)"

    var SQL_DROP_TABLE_USER = "DROP TABLE IF EXISTS user"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // EXECUTE WHEN VERSION IS UPGRADED
        db?.execSQL(SQL_DROP_TABLE_USER)
        db?.execSQL(SQL_CREATE_TABLE_USER)
    }
}