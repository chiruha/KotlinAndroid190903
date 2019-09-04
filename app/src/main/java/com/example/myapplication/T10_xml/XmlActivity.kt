package com.example.myapplication.T10_xml

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_xml.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader

class XmlActivity : AppCompatActivity() {

    data class WeatherData(var hour:Int, var day:Int,
                           var temp:Float, var wfKor:String)
    val weatherList = ArrayList<WeatherData>()
    enum class WeatherDataType{
        None, Hour, Day, Temp, WfKor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml)

        btnXml.setOnClickListener {
            WeatherTask().execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000")
        }
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
//            val path = params[0]!!
//            val url = URL(path)
            val factory = XmlPullParserFactory.newInstance()
            val xpp = factory.newPullParser()
//            xpp.setInput(url.openStream(), "utf-8")
            xpp.setInput(StringReader(weatherString))
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

val weatherString2 = """
<music>
<song>
<id>1</id>
<title>Someone Like You</title>
<artist>Adele</artist>
<duration>4:47</duration>
<thumb_url>
https://api.androidhive.info/music/images/adele.png
</thumb_url>
</song>
<song>
<id>2</id>
<title>Space Bound</title>
<artist>Eminem</artist>
<duration>4:38</duration>
<thumb_url>
https://api.androidhive.info/music/images/eminem.png
</thumb_url>
</song>
<song>
<id>3</id>
<title>Stranger In Moscow</title>
<artist>Michael Jackson</artist>
<duration>5:44</duration>
<thumb_url>https://api.androidhive.info/music/images/mj.png</thumb_url>
</song>
<song>
<id>4</id>
<title>Love The Way You Lie</title>
<artist>Rihanna</artist>
<duration>4:23</duration>
<thumb_url>
https://api.androidhive.info/music/images/rihanna.png
</thumb_url>
</song>
<song>
<id>5</id>
<title>Khwaja Mere Khwaja</title>
<artist>A R Rehman</artist>
<duration>6:58</duration>
<thumb_url>
https://api.androidhive.info/music/images/arrehman.png
</thumb_url>
</song>
<song>
<id>6</id>
<title>All My Days</title>
<artist>Alexi Murdoch</artist>
<duration>4:47</duration>
<thumb_url>
https://api.androidhive.info/music/images/alexi_murdoch.png
</thumb_url>
</song>
<song>
<id>7</id>
<title>Life For Rent</title>
<artist>Dido</artist>
<duration>3:41</duration>
<thumb_url>https://api.androidhive.info/music/images/dido.png</thumb_url>
</song>
<song>
<id>8</id>
<title>Love To See You Cry</title>
<artist>Enrique Iglesias</artist>
<duration>4:07</duration>
<thumb_url>
https://api.androidhive.info/music/images/enrique.png
</thumb_url>
</song>
<song>
<id>9</id>
<title>The Good, The Bad And The Ugly</title>
<artist>Ennio Morricone</artist>
<duration>2:42</duration>
<thumb_url>
https://api.androidhive.info/music/images/ennio.png
</thumb_url>
</song>
<song>
<id>10</id>
<title>Show me the meaning</title>
<artist>Backstreet Boys</artist>
<duration>3:56</duration>
<thumb_url>
https://api.androidhive.info/music/images/backstreet_boys.png
</thumb_url>
</song>
<song>
<id>11</id>
<title>Someone Like You</title>
<artist>Adele</artist>
<duration>4:47</duration>
<thumb_url>
https://api.androidhive.info/music/images/adele.png
</thumb_url>
</song>
<song>
<id>12</id>
<title>Space Bound</title>
<artist>Eminem</artist>
<duration>4:38</duration>
<thumb_url>
https://api.androidhive.info/music/images/eminem.png
</thumb_url>
</song>
<song>
<id>13</id>
<title>Stranger In Moscow</title>
<artist>Michael Jackson</artist>
<duration>5:44</duration>
<thumb_url>https://api.androidhive.info/music/images/mj.png</thumb_url>
</song>
<song>
<id>14</id>
<title>Love The Way You Lie</title>
<artist>Rihanna</artist>
<duration>4:23</duration>
<thumb_url>
https://api.androidhive.info/music/images/rihanna.png
</thumb_url>
</song>
<song>
<id>15</id>
<title>Khwaja Mere Khwaja</title>
<artist>A R Rehman</artist>
<duration>6:58</duration>
<thumb_url>
https://api.androidhive.info/music/images/arrehman.png
</thumb_url>
</song>
<song>
<id>16</id>
<title>All My Days</title>
<artist>Alexi Murdoch</artist>
<duration>4:47</duration>
<thumb_url>
https://api.androidhive.info/music/images/alexi_murdoch.png
</thumb_url>
</song>
<song>
<id>17</id>
<title>Life For Rent</title>
<artist>Dido</artist>
<duration>3:41</duration>
<thumb_url>https://api.androidhive.info/music/images/dido.png</thumb_url>
</song>
<song>
<id>18</id>
<title>Love To See You Cry</title>
<artist>Enrique Iglesias</artist>
<duration>4:07</duration>
<thumb_url>
https://api.androidhive.info/music/images/enrique.png
</thumb_url>
</song>
<song>
<id>19</id>
<title>The Good, The Bad And The Ugly</title>
<artist>Ennio Morricone</artist>
<duration>2:42</duration>
<thumb_url>
https://api.androidhive.info/music/images/ennio.png
</thumb_url>
</song>
<song>
<id>20</id>
<title>Show me the meaning</title>
<artist>Backstreet Boys</artist>
<duration>3:56</duration>
<thumb_url>
https://api.androidhive.info/music/images/backstreet_boys.png
</thumb_url>
</song>
<song>
<id>21</id>
<title>Someone Like You</title>
<artist>Adele</artist>
<duration>4:47</duration>
<thumb_url>
https://api.androidhive.info/music/images/adele.png
</thumb_url>
</song>
<song>
<id>22</id>
<title>Space Bound</title>
<artist>Eminem</artist>
<duration>4:38</duration>
<thumb_url>
https://api.androidhive.info/music/images/eminem.png
</thumb_url>
</song>
<song>
<id>23</id>
<title>Stranger In Moscow</title>
<artist>Michael Jackson</artist>
<duration>5:44</duration>
<thumb_url>https://api.androidhive.info/music/images/mj.png</thumb_url>
</song>
<song>
<id>24</id>
<title>Love The Way You Lie</title>
<artist>Rihanna</artist>
<duration>4:23</duration>
<thumb_url>
https://api.androidhive.info/music/images/rihanna.png
</thumb_url>
</song>
<song>
<id>25</id>
<title>Khwaja Mere Khwaja</title>
<artist>A R Rehman</artist>
<duration>6:58</duration>
<thumb_url>
https://api.androidhive.info/music/images/arrehman.png
</thumb_url>
</song>
<song>
<id>26</id>
<title>All My Days</title>
<artist>Alexi Murdoch</artist>
<duration>4:47</duration>
<thumb_url>
https://api.androidhive.info/music/images/alexi_murdoch.png
</thumb_url>
</song>
<song>
<id>27</id>
<title>Life For Rent</title>
<artist>Dido</artist>
<duration>3:41</duration>
<thumb_url>https://api.androidhive.info/music/images/dido.png</thumb_url>
</song>
<song>
<id>28</id>
<title>Love To See You Cry</title>
<artist>Enrique Iglesias</artist>
<duration>4:07</duration>
<thumb_url>
https://api.androidhive.info/music/images/enrique.png
</thumb_url>
</song>
<song>
<id>29</id>
<title>The Good, The Bad And The Ugly</title>
<artist>Ennio Morricone</artist>
<duration>2:42</duration>
<thumb_url>
https://api.androidhive.info/music/images/ennio.png
</thumb_url>
</song>
<song>
<id>30</id>
<title>Show me the meaning</title>
<artist>Backstreet Boys</artist>
<duration>3:56</duration>
<thumb_url>
https://api.androidhive.info/music/images/backstreet_boys.png
</thumb_url>
</song>
</music>
""".trimIndent()


val weatherString = """
<rss version="2.0">
<channel>
<title>기상청 동네예보 웹서비스 - 서울특별시 구로구 구로제1동 도표예보</title>
<link>http://www.kma.go.kr/weather/main.jsp</link>
<description>동네예보 웹서비스</description>
<language>ko</language>
<generator>동네예보</generator>
<pubDate>2019년 09월 04일 (수)요일 17:00</pubDate>
<item>
<author>기상청</author>
<category>서울특별시 구로구 구로제1동</category>
<title>동네예보(도표) : 서울특별시 구로구 구로제1동 [X=58,Y=125]</title>
<description>
<header>
<tm>201909041700</tm>
<ts>5</ts>
<x>58</x>
<y>125</y>
</header>
<body>
<data seq="0">
<hour>21</hour>
<day>0</day>
<temp>24.0</temp>
<tmx>-999.0</tmx>
<tmn>-999.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>90</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>0.9</ws>
<wd>2</wd>
<wdKor>동</wdKor>
<wdEn>E</wdEn>
<reh>95</reh>
<r06>19.0</r06>
<s06>0.0</s06>
</data>
<data seq="1">
<hour>24</hour>
<day>0</day>
<temp>24.0</temp>
<tmx>-999.0</tmx>
<tmn>-999.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>90</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.0</ws>
<wd>3</wd>
<wdKor>남동</wdKor>
<wdEn>SE</wdEn>
<reh>90</reh>
<r06>19.0</r06>
<s06>0.0</s06>
</data>
<data seq="2">
<hour>3</hour>
<day>1</day>
<temp>24.0</temp>
<tmx>28.0</tmx>
<tmn>23.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>80</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.0</ws>
<wd>2</wd>
<wdKor>동</wdKor>
<wdEn>E</wdEn>
<reh>90</reh>
<r06>9.0</r06>
<s06>0.0</s06>
</data>
<data seq="3">
<hour>6</hour>
<day>1</day>
<temp>23.0</temp>
<tmx>28.0</tmx>
<tmn>23.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>70</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.0</ws>
<wd>3</wd>
<wdKor>남동</wdKor>
<wdEn>SE</wdEn>
<reh>90</reh>
<r06>9.0</r06>
<s06>0.0</s06>
</data>
<data seq="4">
<hour>9</hour>
<day>1</day>
<temp>24.0</temp>
<tmx>28.0</tmx>
<tmn>23.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>70</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.4000000000000001</ws>
<wd>3</wd>
<wdKor>남동</wdKor>
<wdEn>SE</wdEn>
<reh>90</reh>
<r06>20.0</r06>
<s06>0.0</s06>
</data>
<data seq="5">
<hour>12</hour>
<day>1</day>
<temp>26.0</temp>
<tmx>28.0</tmx>
<tmn>23.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>70</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.7000000000000002</ws>
<wd>4</wd>
<wdKor>남</wdKor>
<wdEn>S</wdEn>
<reh>80</reh>
<r06>20.0</r06>
<s06>0.0</s06>
</data>
<data seq="6">
<hour>15</hour>
<day>1</day>
<temp>27.0</temp>
<tmx>28.0</tmx>
<tmn>23.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>70</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.6</ws>
<wd>4</wd>
<wdKor>남</wdKor>
<wdEn>S</wdEn>
<reh>85</reh>
<r06>4.0</r06>
<s06>0.0</s06>
</data>
<data seq="7">
<hour>18</hour>
<day>1</day>
<temp>26.0</temp>
<tmx>28.0</tmx>
<tmn>23.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>70</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.3</ws>
<wd>4</wd>
<wdKor>남</wdKor>
<wdEn>S</wdEn>
<reh>85</reh>
<r06>4.0</r06>
<s06>0.0</s06>
</data>
<data seq="8">
<hour>21</hour>
<day>1</day>
<temp>25.0</temp>
<tmx>28.0</tmx>
<tmn>23.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>70</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>0.8</ws>
<wd>2</wd>
<wdKor>동</wdKor>
<wdEn>E</wdEn>
<reh>90</reh>
<r06>9.0</r06>
<s06>0.0</s06>
</data>
<data seq="9">
<hour>24</hour>
<day>1</day>
<temp>25.0</temp>
<tmx>28.0</tmx>
<tmn>23.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>70</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>0.9</ws>
<wd>4</wd>
<wdKor>남</wdKor>
<wdEn>S</wdEn>
<reh>90</reh>
<r06>9.0</r06>
<s06>0.0</s06>
</data>
<data seq="10">
<hour>3</hour>
<day>2</day>
<temp>24.0</temp>
<tmx>30.0</tmx>
<tmn>24.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>70</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>0.9</ws>
<wd>2</wd>
<wdKor>동</wdKor>
<wdEn>E</wdEn>
<reh>90</reh>
<r06>9.0</r06>
<s06>0.0</s06>
</data>
<data seq="11">
<hour>6</hour>
<day>2</day>
<temp>24.0</temp>
<tmx>30.0</tmx>
<tmn>24.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>70</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>0.9</ws>
<wd>1</wd>
<wdKor>북동</wdKor>
<wdEn>NE</wdEn>
<reh>95</reh>
<r06>9.0</r06>
<s06>0.0</s06>
</data>
<data seq="12">
<hour>9</hour>
<day>2</day>
<temp>25.0</temp>
<tmx>30.0</tmx>
<tmn>24.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>70</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.3</ws>
<wd>0</wd>
<wdKor>북</wdKor>
<wdEn>N</wdEn>
<reh>90</reh>
<r06>4.0</r06>
<s06>0.0</s06>
</data>
<data seq="13">
<hour>12</hour>
<day>2</day>
<temp>28.0</temp>
<tmx>30.0</tmx>
<tmn>24.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>60</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.5</ws>
<wd>1</wd>
<wdKor>북동</wdKor>
<wdEn>NE</wdEn>
<reh>75</reh>
<r06>4.0</r06>
<s06>0.0</s06>
</data>
<data seq="14">
<hour>15</hour>
<day>2</day>
<temp>29.0</temp>
<tmx>30.0</tmx>
<tmn>24.0</tmn>
<sky>4</sky>
<pty>1</pty>
<wfKor>비</wfKor>
<wfEn>Rain</wfEn>
<pop>60</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.6</ws>
<wd>2</wd>
<wdKor>동</wdKor>
<wdEn>E</wdEn>
<reh>70</reh>
<r06>4.0</r06>
<s06>0.0</s06>
</data>
<data seq="15">
<hour>18</hour>
<day>2</day>
<temp>28.0</temp>
<tmx>30.0</tmx>
<tmn>24.0</tmn>
<sky>3</sky>
<pty>0</pty>
<wfKor>구름 많음</wfKor>
<wfEn>Mostly Cloudy</wfEn>
<pop>20</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.3</ws>
<wd>1</wd>
<wdKor>북동</wdKor>
<wdEn>NE</wdEn>
<reh>80</reh>
<r06>4.0</r06>
<s06>0.0</s06>
</data>
<data seq="16">
<hour>21</hour>
<day>2</day>
<temp>27.0</temp>
<tmx>30.0</tmx>
<tmn>24.0</tmn>
<sky>3</sky>
<pty>0</pty>
<wfKor>구름 많음</wfKor>
<wfEn>Mostly Cloudy</wfEn>
<pop>20</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.1</ws>
<wd>2</wd>
<wdKor>동</wdKor>
<wdEn>E</wdEn>
<reh>80</reh>
<r06>0.0</r06>
<s06>0.0</s06>
</data>
<data seq="17">
<hour>24</hour>
<day>2</day>
<temp>27.0</temp>
<tmx>30.0</tmx>
<tmn>24.0</tmn>
<sky>4</sky>
<pty>0</pty>
<wfKor>흐림</wfKor>
<wfEn>Cloudy</wfEn>
<pop>30</pop>
<r12>0.0</r12>
<s12>0.0</s12>
<ws>1.2000000000000002</ws>
<wd>3</wd>
<wdKor>남동</wdKor>
<wdEn>SE</wdEn>
<reh>80</reh>
<r06>0.0</r06>
<s06>0.0</s06>
</data>
</body>
</description>
</item>
</channel>
</rss>
""".trimIndent()