package by.itacademy.pvt.dz2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import by.itacademy.pvt.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_dz2.*

class Dz2Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)
        findViewById<View>(R.id.loadImageButton)
            .setOnClickListener {
                loadImage(oneProgressBar)
            }
    }
    private fun loadImage(progressBar: ProgressBar) {
        progressBar.visibility = ProgressBar.VISIBLE
        Picasso.get()
            .load("https://avatars.mds.yandex.net/get-pdb/964669/92f6c91e-4003-4b9c-abe8-88b5f0b2e96d/orig")
            .transform(CropCircleTransformation())
            .into(loadImageView)
        progressBar.visibility = ProgressBar.GONE
    }
}