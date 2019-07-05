package by.itacademy.pvt.dz8

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.utils.loadCircleImage

class Dz8ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = view.findViewById<ImageView>(R.id.dz8ItemImageView)
    private val textView = view.findViewById<TextView>(R.id.dz8ItemTextView)

    fun bind(item: Student) {
        loadCircleImage(itemView.context, item.imageUrl, imageView)
        textView.text = item.name
    }
}