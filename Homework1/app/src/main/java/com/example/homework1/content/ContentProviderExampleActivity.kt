package com.example.homework1.content

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework1.R
import com.google.android.material.button.MaterialButton

/**
 * Экран позволяет с помощью MyContentProvider:
 * - сохранять данные в таблицу бд SQLite
 * - выводить сохраненные данные
 *
 * Примеры использования ContentProvider в приложениях - Телефонная книга, Сообщения.
 * */
class ContentProviderExampleActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var etValue: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider_example)

        val btnSave: MaterialButton = findViewById(R.id.btn_save)
        val btnLoad: MaterialButton = findViewById(R.id.btn_load)
        etValue = findViewById(R.id.et_value)
        tvResult = findViewById(R.id.tv_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnSave.setOnClickListener {
           saveData()
        }

        btnLoad.setOnClickListener {
            loadDataFromContentProvider()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun saveData() {
        val values = ContentValues()
        values.put(MyContentProvider.name, etValue.text.toString())

        contentResolver.insert(MyContentProvider.CONTENT_URI, values)
        Toast.makeText(this, getString(R.string.saved), Toast.LENGTH_SHORT).show()
    }

    private fun loadDataFromContentProvider() {
        val cursor = contentResolver.query(Uri.parse(MyContentProvider.URL) , null, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val str = StringBuilder()
                while (!cursor.isAfterLast) {
                    val name = cursor.getColumnIndex("name")
                    str.append(cursor.getString(name) + "\n")
                    cursor.moveToNext()
                }
                tvResult.text = str
            }
        }
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, ContentProviderExampleActivity::class.java)
        }
    }
}