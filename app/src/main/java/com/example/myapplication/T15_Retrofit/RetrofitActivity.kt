package com.example.myapplication.T15_Retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyTypicodeInterface {
    @GET("posts")
    fun getPosts(): Call<List<MyPost>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int) : Call<MyPost>

    @POST("posts")
    fun createPost(@Body post: MyPost) : Call<MyPost>

}

data class MyPost(val userId: Int, val id: Int, val title: String, val body: String)

class RetrofitActivity : AppCompatActivity() {

    lateinit var myTypicode: MyTypicodeInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        myTypicode = retrofit.create(MyTypicodeInterface::class.java)
        getPosts()
        getPost(20)
        createPost()
    }

    private fun getPost(id: Int) {
        val call = myTypicode.getPost(id)
        call.enqueue(object : Callback<MyPost>{
            override fun onFailure(call: Call<MyPost>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MyPost>, response: Response<MyPost>) {
                val post = response.body()
                post?.let{
                    Log.d("getPost", post.toString())
                }
            }

        })
    }

    private fun getPosts() {
        val call = myTypicode.getPosts()
        call.enqueue(object : Callback<List<MyPost>> {
            override fun onFailure(call: Call<List<MyPost>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<MyPost>>, response: Response<List<MyPost>>) {
                if(response.isSuccessful == false) {
                    Toast.makeText(this@RetrofitActivity, "error : ${response.code()}", Toast.LENGTH_SHORT).show()
                    return
                }

                val posts = response.body()
                posts?.let{
                    for(post in posts) {
                        Log.d("post", post.toString())
                    }
                }
            }
        })
    }

    private fun createPost() {
        val post = MyPost(11,12, "my title", "my text")
        val call = myTypicode.createPost(post)
        call.enqueue(object: Callback<MyPost>{
            override fun onFailure(call: Call<MyPost>, t: Throwable) {

            }

            override fun onResponse(call: Call<MyPost>, response: Response<MyPost>) {

            }

        })
    }




}
