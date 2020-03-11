package com.example.jsonwork

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {
    private val TAG: String = "Read File Data"
    private lateinit var dataView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataView = findViewById(R.id.dataView)
        loadJSONFromAsset()
    }

    private fun loadJSONFromAsset() {
        //function to load the JSON from the Asset and return the object
        var data: String? = null
        try {
            val jsonFileLoad = assets.open("json.txt")
            val inputStreamReader = InputStreamReader(jsonFileLoad)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var str: String?
            while (bufferedReader.readLine().also { str = it } != null) {
                if (str != "|") {
                    stringBuilder.append(str)
                    data = stringBuilder.toString()
                    eventrap(data)
                    //str = bufferedReader.readLine()
                    // Log.e(TAG, "" + data)
                } else {
                    stringBuilder.delete(0, stringBuilder.length)
                }

            }
            jsonFileLoad.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    fun eventrap(data: String) {

        //data holding list
        val bpList = mutableListOf<BpData>()
        val gyList = mutableListOf<GyData>()
        val acList = mutableListOf<AcData>()
        val atList = mutableListOf<AtData>()
        val mgList = mutableListOf<MgData>()
        val irList = mutableListOf<IrData>()
        val hyList = mutableListOf<HyData>()
        val machineidList = mutableListOf<Machineid>()

        try {
            val obj = JSONObject(data)
            val subassemblies: JSONObject = obj.getJSONObject("subassemblies")
            val componentAnalyzer1: JSONObject = subassemblies.getJSONObject("componentAnalyzer1")
            val collectors: JSONObject = componentAnalyzer1.getJSONObject("collectors")
            //bp1
            val bp1: JSONObject = collectors.getJSONObject("bp1")
            var csvArray = bp1.getJSONArray("CSV")
            for (i in 0 until csvArray.length()) {
                val bpArray = csvArray.getJSONArray(i)
                Log.e("timestamp-bp1", bpArray.getString(0))
                Log.e("value-bp1", bpArray.getString(1))

                //Store into the list
                bpList.add(
                    BpData(
                        TimeStampBp1 = bpArray.getString(0),
                        data = bpArray.getString(1)
                    )
                )
            }
            //gy1
            val gy1 = collectors.getJSONObject("gy1")
            csvArray = gy1.getJSONArray("CSV")
            for (i in 0 until csvArray.length()) {
                val gyArray = csvArray.getJSONArray(i)
                Log.e("timestamp-gy1", gyArray.getString(0))
                Log.e("value-gy1", gyArray.getString(1))
                Log.e("value-gy1", gyArray.getString(2))
                Log.e("value-gy1", gyArray.getString(3))

                // Store into the list
                gyList.add(
                    GyData(
                        TimeStampGy1 = gyArray.getString(0),
                        data1 = gyArray.getString(1),
                        data2 = gyArray.getString(2),
                        data3 = gyArray.getString(3)
                    )
                )
            }

            //ac1
            val ac1 = collectors.getJSONObject("ac1")
            csvArray = ac1.getJSONArray("CSV")
            for (i in 0 until csvArray.length()) {
                val acArray = csvArray.getJSONArray(i)
                Log.e("timestamp-ac1", acArray.getString(0))
                Log.e("value-ac1", acArray.getString(1))
                Log.e("value-ac1", acArray.getString(2))
                Log.e("value-ac1", acArray.getString(3))

                // Store into the list
                acList.add(
                    AcData(
                        TimeStampAc1 = acArray.getString(0),
                        data1 = acArray.getString(1),
                        data2 = acArray.getString(2),
                        data3 = acArray.getString(3)
                    )
                )
            }

            //at1
            val at1: JSONObject = collectors.getJSONObject("at1")
            csvArray = at1.getJSONArray("CSV")
            for (i in 0 until csvArray.length()) {
                val at1Array = csvArray.getJSONArray(i)
                Log.e("TimeStamp-at1", at1Array.optString(0))

                // Store into the list
                atList.add(
                    AtData(
                        TimeStampAt1 = at1Array.getString(0),
                        data = at1Array.getString(1)
                    )
                )
            }

            //mg1
            val mg1 = collectors.getJSONObject("mg1")
            csvArray = mg1.getJSONArray("CSV")
            for (i in 0 until csvArray.length()) {
                val mgArray = csvArray.getJSONArray(i)
                Log.e("timestamp-mg1", mgArray.getString(0))
                Log.e("value-mg1", mgArray.getString(1))
                Log.e("value-mg1", mgArray.getString(2))
                Log.e("value-mg1", mgArray.getString(3))

                // Store into the list
                mgList.add(
                    MgData(
                        TimeStampMg1 = mgArray.getString(0),
                        data1 = mgArray.getString(1),
                        data2 = mgArray.getString(2),
                        data3 = mgArray.getString(3)
                    )
                )
            }

            //ir1
            val ir1 = collectors.getJSONObject("ir1")
            csvArray = ir1.getJSONArray("CSV")
            for (i in 0 until csvArray.length()) {
                val irArray = csvArray.getJSONArray(i)
                Log.e("timestamp-ir1", irArray.getString(0))
                Log.e("value-ir1", irArray.getString(1))
                Log.e("value-ir1", irArray.getString(2))

                // Store into the list
                irList.add(
                    IrData(
                        TimeStampIr1 = irArray.getString(0),
                        data1 = irArray.getString(1),
                        data2 = irArray.getString(2)
                    )
                )
            }

            //hy1
            val hy1 = collectors.getJSONObject("hy1")
            csvArray = hy1.getJSONArray("CSV")
            for (i in 0 until csvArray.length()) {
                val hyArray = csvArray.getJSONArray(i)
                Log.e("timestamp-hy1", hyArray.getString(0))
                Log.e("value-hy1", hyArray.getString(1))

                // Store into the list
                hyList.add(
                    HyData(
                        TimeStampHy1 = hyArray.getString(0),
                        data = hyArray.getString(1)

                    )
                )

            }
            val header: JSONObject = obj.getJSONObject("header")
            val machineId = header.getString("machineId")
            val machineName = header.getString("machineName")
            Log.e("machineId", "machineId" + machineId)
            Log.e("machineName", "machineName" + machineName)

            // Store into the list
            machineidList.add(
                Machineid(
                    data = header.getString("machineId")
                )
            )

        } catch (e: JSONException) {
            Log.d(TAG, "Error : ${e.message}")
            e.printStackTrace()
        }
    }
}























