package by.itacademy.pvt.dz0

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import by.itacademy.R


class Dz0MainActivity : Activity(), View.OnClickListener {


    private var hw: TextView? = null
    private var wh: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dz0)
        val invertButton = findViewById<Button>(R.id.buttInversion)
        invertButton.setOnClickListener(this)

        hw = findViewById(R.id.helloworld)
        wh = findViewById(R.id.worldhello)

        hw?.setText(R.string.hello_world)
        wh?.setText(R.string.world_hello)


        hw?.setOnClickListener {inversion()}


        val worldHelloClickListener = View.OnClickListener { inversion() }
        wh?.setOnClickListener(worldHelloClickListener)
    }


    override fun onClick(v: View) {
        inversion()
    }

    fun inversion() {

        val hw1 = hw?.text.toString()
        val wh2 = wh?.text.toString()

        val color1 = hw?.background
        val color2 = wh?.background

        hw?.background = color2
        wh?.background = color1

        hw?.text = wh2
        wh?.text = hw1
    }
}
