package com.example.homework1.content

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

class MyContentProvider : ContentProvider() {

    private var db: SQLiteDatabase? = null

    companion object {

        private val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private val values: HashMap<String, String>? = null

        private const val AUTHORITIES = "com.example.user.provider"

        const val URL = "content://$AUTHORITIES/users"
        const val id = "id"
        const val name = "name"
        const val uriCode = 1
        const val TABLE_NAME = "Users"

        val CONTENT_URI: Uri = Uri.parse(URL)

        init {

            uriMatcher.addURI(
                AUTHORITIES,
                "users",
                uriCode
            )

            uriMatcher.addURI(
                AUTHORITIES,
                "users/*",
                uriCode
            )
        }
    }

    override fun onCreate(): Boolean {
        val context = context
        val dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
        return db != null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var order = sortOrder
        val qb = SQLiteQueryBuilder()
        qb.tables = TABLE_NAME
        when (uriMatcher.match(uri)) {
            uriCode -> qb.projectionMap = values
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        if (order == null || order === "") {
            order = id
        }
        val cursor = qb.query(
            db, projection, selection, selectionArgs, null,
            null, order
        )
        cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String {
        return when (uriMatcher.match(uri)) {
            uriCode -> "com.android.cursor.dir/values"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val rowID = db?.insert(TABLE_NAME, "", values)
        if (rowID != null && rowID > 0) {
            val resultUri =
                ContentUris.withAppendedId(CONTENT_URI, rowID)
            context?.contentResolver?.notifyChange(resultUri, null) ?: return null
            return resultUri
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val context = context ?: return 0
        val count: Int = when (uriMatcher.match(uri)) {
            uriCode -> db!!.delete(TABLE_NAME, selection, selectionArgs)
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        val count = when (uriMatcher.match(uri)) {
            uriCode -> db!!.update(TABLE_NAME, values, selection, selectionArgs)
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }
}