package com.example.homework1.content

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(
    context,
    MyContentProvider.DATABASE_NAME,
    null,
    MyContentProvider.DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(MyContentProvider.QUERY_CREATE_DB_TABLE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS ${MyContentProvider.TABLE_NAME}")
        onCreate(db)
    }
}