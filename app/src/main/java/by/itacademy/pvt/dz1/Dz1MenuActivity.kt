package by.itacademy.pvt.dz1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.itacademy.pvt.R
import by.itacademy.pvt.dz0.Dz0MainActivity
import by.itacademy.pvt.dz2.Dz2Activity
import by.itacademy.pvt.dz3.Dz3Activity
import by.itacademy.pvt.dz4.Dz4Activity
import by.itacademy.pvt.dz5.Dz5Activity
import by.itacademy.pvt.dz5.dz5_sova.Dz5SovaActivity
import by.itacademy.pvt.dz6.Dz6StudentListActivity
import by.itacademy.pvt.dz8.Dz8Activity
import by.itacademy.pvt.dz9.Dz9MapsActivity

class Dz1MenuActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz1_menu)

        findViewById<View>(R.id.dzOneButton)
            .setOnClickListener {
                viewDz0()
            }
        findViewById<View>(R.id.dzTwoButton)
            .setOnClickListener {
                viewDz1()
            }
        findViewById<View>(R.id.dzThreeButton)
            .setOnClickListener {
                viewDz2()
            }
        findViewById<View>(R.id.dzFourButton)
            .setOnClickListener {
                viewDz3()
            }
        findViewById<View>(R.id.dzFiveButton)
            .setOnClickListener {
                viewDz4()
            }
        findViewById<View>(R.id.dzSixButton)
            .setOnClickListener {
                viewDz5()
            }
        findViewById<View>(R.id.dzSixSovaButton)
            .setOnClickListener {
                viewDz5Sova()
            }
        findViewById<View>(R.id.dzSevenButton)
            .setOnClickListener {
                viewDz6()
            }

        findViewById<View>(R.id.dzEightButton)
            .setOnClickListener {
                viewDz8()
            }
        findViewById<View>(R.id.dzNineButton)
            .setOnClickListener {
                viewDz9()
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

    private fun viewDz3() {
        val intent = Intent(this, Dz3Activity::class.java)
        startActivity(intent)
    }

    private fun viewDz4() {
        val intent = Intent(this, Dz4Activity::class.java)
        startActivity(intent)
    }

    private fun viewDz5() {
        val intent = Intent(this, Dz5Activity::class.java)
        startActivity(intent)
    }

    private fun viewDz5Sova() {
        val intent = Intent(this, Dz5SovaActivity::class.java)
        startActivity(intent)
    }

    private fun viewDz6() {
        val intent = Intent(this, Dz6StudentListActivity::class.java)
        startActivity(intent)
    }

    private fun viewDz8() {
        val intent = Intent(this, Dz8Activity::class.java)
        startActivity(intent)
    }

    private fun viewDz9() {
        val intent = Intent(this, Dz9MapsActivity::class.java)
        startActivity(intent)
    }
}