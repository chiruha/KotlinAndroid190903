package com.example.myapplication.Test_WeatherRecyclerView

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_weather_recycler_view.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL

class WeatherRecyclerViewActivity : AppCompatActivity() {

    data class WeatherData(var hour: Int, var day: Int, var temp: Float, var wfKor: String)
    val weatherList = ArrayList<WeatherData>()

    enum class WeatherDataType{
        None, Hour, Day, Temp, WfKor
    }

    inner class WeatherTask: AsyncTask<String, Unit, String>(){
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

           // weatherTextView.setText("")
            for(data in weatherList){
               // weatherTextView.append(data.toString()+"\n")
                Log.d("날씨 : ", "${data.toString()}")
            }

            Log.d("WeatherTask", "WeatherTask=====================")
        }

        override fun doInBackground(vararg params: String?): String {
            var res = ""
            val path = params[0]!!
            val url = URL(path)
            val factory = XmlPullParserFactory.newInstance()
            val xpp = factory.newPullParser()
            xpp.setInput(url.openStream(), "utf-8")
//            xpp.setInput(StringReader(weatherString))
            var eventType = xpp.eventType
            var type = WeatherDataType.None
            var data = WeatherData(0, 0, 0F, "")

            while (eventType != XmlPullParser.END_DOCUMENT){
                if(eventType == XmlPullParser.START_TAG){
                    type = when(xpp.name){
                        "hour"->{ WeatherDataType.Hour }
                        "day"->{ WeatherDataType.Day}
                        "temp"->{ WeatherDataType.Temp }
                        "wfKor"->{ WeatherDataType.WfKor }
                        "data"->{
                            data = WeatherData(0, 0, 0F, "")
                            weatherList.add(data)
                            WeatherDataType.None
                        }
                        else->{ WeatherDataType.None }
                    }
                }else if(eventType == XmlPullParser.TEXT){
                    when(type){
                        WeatherDataType.Hour->{ data.hour = xpp.text.toInt()}
                        WeatherDataType.Day ->{ data.day = xpp.text.toInt() }
                        WeatherDataType.Temp->{ data.temp = xpp.text.toFloat()}
                        WeatherDataType.WfKor->{ data.wfKor = xpp.text }
                    }
                    type = WeatherDataType.None
                }
                eventType = xpp.next()
            }
            return res
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_recycler_view)

        WeatherTask().execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000")
        generateData()

        weatherRecyclerView.adapter = WeatherRecyclerViewAdapter(weatherList)
        weatherRecyclerView.layoutManager = LinearLayoutManager(this)

        /*
        *
        myRecyclerView.adapter = MyRecyclerViewAdapter(myList)
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        * */

        Log.d("onCreate", "onCreate=====================")
    }

    private fun generateData(){
//        val icons = arrayOf( R.drawable.ic_3d_rotation_black_24dp,
//            R.drawable.ic_ac_unit_black_24dp,
//            R.drawable.ic_access_alarm_black_24dp )
//
//        for(i in 0..100){
//            val icon = icons[i%3]
//            myList.add(
//                MyData("title $i", "desc $i", icon)
//            )
//        }
    }


}
