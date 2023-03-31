package com.ucb.examen2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*

class MainActivity : AppCompatActivity() {
    val restApiAdapter = RestApiAdapter()
    val endPoint = restApiAdapter.connectionApi()
    val bookResponseCall = endPoint.getAllPost()
    lateinit var recyclerView: RecyclerView
    val listOfPosts = ArrayList<Post>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



         bookResponseCall.enqueue( object : Callback<List<Post>> {
             @Override
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t?.printStackTrace()
            }
             @Override
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val posts = response?.body()
                Log.d("RESP POST", Gson().toJson(posts))
                posts?.forEach {
                    Log.d("RESP BODY", it.body)
                    listOfPosts.add(it)
                }
                 val linearLayoutManager = LinearLayoutManager(applicationContext)
                 linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

                 recyclerView = findViewById(R.id.postList)
                 recyclerView.layoutManager = linearLayoutManager

                 recyclerView.adapter = PostListAdapter(listOfPosts, applicationContext)
            }
        })

        GlobalScope.launch {
            val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
            val repository = BookRepository(bookDao)
            repository.insert(Book("the best seller: Android"))
            val lista = repository.getListBooks()
            lista.forEach {
                Log.d("DBTEST","Id book = ${it.id}, Title: ${it.title}")
            }
        }


    }
}