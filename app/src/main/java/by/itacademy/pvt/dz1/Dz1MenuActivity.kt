package by.itacademy.pvt.dz1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.itacademy.pvt.R
import by.itacademy.pvt.dz0.Dz0MainActivity
import by.itacademy.pvt.dz2.Dz2Activity

class Dz1MenuActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz1_menu)

        findViewById<View>(R.id.oneButton)
            .setOnClickListener {
                viewDz0()
            }
        findViewById<View>(R.id.twoButton)
            .setOnClickListener {
                viewDz1()
            }
        findViewById<View>(R.id.threeButton)
            .setOnClickListener {
                viewDz2()
            }
    }
    private fun viewDz0() {
        val intent = Intent(this, Dz0MainActivity::class.java)
        startActivity(intent)
    }
    private fun viewDz1() {
        val intent = Intent(this, Dz1FlagsActivity::class.java)
        startActivity(intent)
    }
    private fun viewDz2() {
        val intent = Intent(this, Dz2Activity::class.java)
        startActivity(intent)
    }
}
