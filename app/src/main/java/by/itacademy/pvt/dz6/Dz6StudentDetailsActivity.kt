package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import by.itacademy.pvt.R
import by.itacademy.pvt.utils.loadCircleImage

private const val STUDENT_ID = "STUDENT_ID"

class Dz6StudentDetailsActivity : Activity() {

    companion object {
        fun getIntent(context: Context, id: String = ""): Intent {
            val intent = Intent(context, Dz6StudentEditActivity::class.java)
            intent.putExtra(STUDENT_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_details)

        val errorId = resources.getString(R.string.dz6_error_id)

        val studentId = intent.getStringExtra(STUDENT_ID)
        val student = SupervisingStudents.findStudentById(studentId)
        val avatarImageView = findViewById<ImageView>(R.id.dz8AvatarImageView)

        if (student != null) {
            loadCircleImage(student.imageUrl, avatarImageView)
            findViewById<TextView>(R.id.dz8NameTextView).text = student.name
            findViewById<TextView>(R.id.dz8AgeTextView).text = student.age.toString()
        }

        findViewById<Button>(R.id.dz8deleteButton).setOnClickListener {
            if (student == null) {
                Toast.makeText(this, errorId, Toast.LENGTH_SHORT).show()
            } else {
                SupervisingStudents.deleteStudentByIdFromList(studentId)
            }
            finish()
        }

        findViewById<Button>(R.id.dz8editButton).setOnClickListener {
            if (student == null) {
                Toast.makeText(this, errorId, Toast.LENGTH_SHORT).show()
            } else {
                startEditActivity()
            }
            finish()
        }
    }

    private fun startEditActivity(id: String = "") {
        val intent = Intent(this, Dz6StudentEditActivity::class.java)
        intent.putExtra(STUDENT_ID, id)
        startActivity(intent)
    }
}