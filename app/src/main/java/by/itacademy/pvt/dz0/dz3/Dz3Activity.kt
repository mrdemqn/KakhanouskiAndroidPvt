package by.itacademy.pvt.dz0.dz3

import android.app.Activity
import android.os.Bundle
import by.itacademy.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_dz3.*
import java.lang.Exception

class Dz3Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz3)
        Picasso.get()
            .load("http://www.adm-mosrentgen.ru/wp-content/uploads/2017/07/%D0%BC%D0%B8%D0%BD%D1%8C%D0%BE%D0%BD%D1%8B-%D0%BF%D1%80%D0%B8%D0%BA%D0%BE%D0%BB%D1%8B-%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8.jpg")
            .resize(350,350)
            .transform(CropCircleTransformation())
            .into(loadAvatarImageView, object : Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {

                }
            })
    }
}