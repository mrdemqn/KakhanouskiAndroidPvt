package by.itacademy.pvt.dz6

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.utils.loadCircleImage

class Dz6ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = view.findViewById<ImageView>(R.id.dz6ItemImageView)
    private val textView = view.findViewById<TextView>(R.id.dz6ItemTextView)

    fun bind(item: Student) {
        loadCircleImage(item.imageUrl, imageView)
        textView.text = item.name
    }
}