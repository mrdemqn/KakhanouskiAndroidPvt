package by.itacademy.pvt.dz2

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import by.itacademy.pvt.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_dz2.*
import java.lang.Exception

class Dz2Activity : Activity() {
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)
        progressBar = findViewById(R.id.oneProgressBar)
        findViewById<Button>(R.id.loadImageButton)
            .setOnClickListener {
                loadImage()
            }
    }
    private fun loadImage() {
        progressBar.visibility = ProgressBar.VISIBLE
        Picasso.get()
            .load("https://avatars.mds.yandex.net/get-pdb/964669/92f6c91e-4003-4b9c-abe8-88b5f0b2e96d/orig")
            .transform(CropCircleTransformation())
            .into(loadImageView, object: Callback {
                override fun onSuccess() {
                    progressBar.visibility = ProgressBar.GONE
                }
                override fun onError(e: Exception?) {
                }
            })
    }
}