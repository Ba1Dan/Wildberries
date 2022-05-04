package com.example.homework1.content

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(QUERY_CREATE_DB_TABLE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS ${MyContentProvider.TABLE_NAME}")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "UserDB"
        private const val DATABASE_VERSION = 1

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"

        private const val QUERY_CREATE_DB_TABLE =
            ("CREATE TABLE " + MyContentProvider.TABLE_NAME
                    + " ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + " $COLUMN_NAME TEXT NOT NULL);")
    }
}