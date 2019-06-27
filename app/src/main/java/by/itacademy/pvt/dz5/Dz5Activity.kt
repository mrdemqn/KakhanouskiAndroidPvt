package by.itacademy.pvt.dz5

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.R

class Dz5Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5)

        val pieChartView = findViewById<Dz5View>(R.id.dz5View)
        pieChartView.pieChartValues = floatArrayOf(30f, 20f, 50f, 60f)
    }
}