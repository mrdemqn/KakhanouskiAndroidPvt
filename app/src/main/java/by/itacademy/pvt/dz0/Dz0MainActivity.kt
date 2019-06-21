package by.itacademy.pvt.dz0

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import by.itacademy.R


class Dz0MainActivity : Activity(), View.OnClickListener {

private var helloWorldTextView: TextView? = null
    private var worldHelloTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dz0)

        val invertButton = findViewById<Button>(R.id.buttInversion)
        invertButton.setOnClickListener(this)

        helloWorldTextView = findViewById(R.id.helloWorldTextView)
        worldHelloTextView = findViewById(R.id.worldHelloTextView)

        helloWorldTextView?.setText(R.string.helloWorldTextView)

        helloWorldTextView?.setOnClickListener {inversion()}

        worldHelloTextView?.setOnClickListener{ inversion() }
    }

    override fun onClick(v: View) {
        inversion()
    }

    fun inversion() {

        val helloWorld1TextView = helloWorldTextView?.text.toString()
        val worldHello2TextView = worldHelloTextView?.text.toString()

        val color1 = helloWorldTextView?.background
        val color2 = worldHelloTextView?.background

        helloWorldTextView?.background = color2
        worldHelloTextView?.background = color1

        helloWorldTextView?.text = worldHello2TextView
        worldHelloTextView?.text = helloWorld1TextView
    }
}
