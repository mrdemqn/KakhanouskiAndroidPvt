package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6StudentListActivity.Companion.ID_EXTRA
import by.itacademy.pvt.utils.loadCircleImage

class Dz6StudentDetailsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_details)

        val studentId = intent.getStringExtra(ID_EXTRA)
        val student = SingletonStudent.findStudentById(studentId)
        val avatarImageView = findViewById<ImageView>(R.id.dz6AvatarImageView)
        if (student != null) {
            loadCircleImage(this@Dz6StudentDetailsActivity, student.imageUrl, avatarImageView)
            findViewById<TextView>(R.id.dz6NameTextView).text = student.name
            findViewById<TextView>(R.id.dz6AgeTextView).text = student.age.toString()
        }

        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            SingletonStudent.deleteStudentByIdFromList(studentId)
            finish()
        }

        findViewById<Button>(R.id.editButton).setOnClickListener {
            val intent = Intent(applicationContext, Dz6StudentEditActivity::class.java)
            intent.putExtra(ID_EXTRA, studentId)
            startActivity(intent)
            finish()
        }
    }
}