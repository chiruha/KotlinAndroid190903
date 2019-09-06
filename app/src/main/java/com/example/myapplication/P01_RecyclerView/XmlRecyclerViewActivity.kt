package com.example.myapplication.P01_RecyclerView

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_xml.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL

class XmlRecyclerViewActivity : AppCompatActivity() {

    data class WeatherData(var hour:Int, var day:Int,
                           var temp:Float, var wfKor:String)
    val weatherList = ArrayList<WeatherData>()
    enum class WeatherDataType{
        None, Hour, Day, Temp, WfKor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_recycler_view)

         WeatherTask().execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000")
    }

    inner class WeatherTask: AsyncTask<String, Unit, String>(){
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            weatherTextView.setText("")
            for(data in weatherList){
                weatherTextView.append(data.toString()+"\n")
            }
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
            var data = WeatherData(0,0,0F,"")

            while (eventType != XmlPullParser.END_DOCUMENT){
                if(eventType == XmlPullParser.START_TAG){
                    type = when(xpp.name){
                        "hour"->{ WeatherDataType.Hour }
                        "day"->{ WeatherDataType.Day}
                        "temp"->{ WeatherDataType.Temp }
                        "wfKor"->{ WeatherDataType.WfKor }
                        "data"->{
                            data = WeatherData(0,0,0F, "")
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
}

