package by.itacademy.pvt.dz5.dz5_sova

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.R
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_dz5_sova.*

class Dz5SovaActivity : Activity() {

    private lateinit var imageViewOpenClose: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5_sova)

        imageViewOpenClose = findViewById<ImageView>(R.id.imageViewAnimationListOpeningClosing)
            .background as AnimationDrawable

        findViewById<ImageView>(R.id.imageViewSelector)
            .setOnClickListener {
                onClick()
            }
    }

    override fun onResume() {
        super.onResume()
        imageViewOpenClose.start()
    }

    override fun onPause() {
        super.onPause()
        imageViewOpenClose.stop()
    }

    fun onClick() {
        imageViewSelector.isActivated = !imageViewSelector.isActivated
    }
}