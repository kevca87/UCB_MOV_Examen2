package com.ucb.examen2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateBook : AppCompatActivity() {
    lateinit var saveButton: Button
    lateinit var titleEditText: EditText
    lateinit var descriptionEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)
        saveButton = findViewById(R.id.button)
        titleEditText = findViewById(R.id.tileTextField)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        saveButton.setOnClickListener{
            var bookTitle = titleEditText.getText().toString()
            var bookDescription = descriptionEditText.getText().toString()
            GlobalScope.launch {

                val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
                val repository = BookRepository(bookDao)
                repository.insert(Book(bookTitle,bookDescription))
                val lista = repository.getListBooks()
                lista.forEach {
                    Log.d("DBTEST","Id book = ${it.id}, Title: ${it.title}, Description: ${it.description}")
                }
            }

        }

    }
}