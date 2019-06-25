package by.itacademy.pvt.dz5.dz5_sova

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.R
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_dz5_sova.*

class Dz5SovaActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5_sova)

        val imageViewOpenClose = findViewById<View>(R.id.imageViewAnimationListOpeningClosing)
                as ImageView
        (imageViewOpenClose.background as AnimationDrawable).start()

        findViewById<View>(R.id.imageViewSelector)
            .setOnClickListener {
                onClick()
            }
    }

    fun onClick() {
        imageViewSelector.isActivated = !imageViewSelector.isActivated
    }
}