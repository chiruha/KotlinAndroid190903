package com.example.myapplication.T09_AsyncTask

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_thread.*

class AsyncTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)

        btnStart.setOnClickListener {
            val task = MyTask()
            task.execute(30)
        }

    }

    inner class MyTask: AsyncTask<Int, Float, String>() {
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            btnStart.setText(result)
        }

        override fun onProgressUpdate(vararg values: Float?) {
            super.onProgressUpdate(*values)
            btnStart.setText("count ${values[0]}")
        }

        override fun doInBackground(vararg params: Int?): String { // vararg 가변인수 : 배열로 보면 됨
            // null 처리 방법1
//            params[0].let {
//                for(i in 0..10) {
//                    Thread.sleep(100)
//                    publishProgress(i.toFloat()) // onProgressUpdate 로 감
//                }
//            }

            // null 처리 방법2
            val start = params[0] ?: 0
            for(i in start..100) {
                Thread.sleep(100)
                publishProgress(i.toFloat()) // onProgressUpdate 로 감
            }

            return "my task done!!!"
        }



    }
}
