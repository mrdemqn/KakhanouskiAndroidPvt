package by.itacademy.pvt.dz0.dz1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.itacademy.R
import by.itacademy.pvt.dz0.dz0.Dz0MainActivity

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
    }

    private fun viewDz0() {
        val intent = Intent(this, Dz0MainActivity::class.java)
        startActivity(intent)
    }

    private fun viewDz1() {
        val intent = Intent(this, Dz1FlagsActivity::class.java)
        startActivity(intent)
    }
}
