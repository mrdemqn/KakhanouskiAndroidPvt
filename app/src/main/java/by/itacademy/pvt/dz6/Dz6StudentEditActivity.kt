package by.itacademy.pvt.dz6

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import by.itacademy.pvt.R

class Dz6StudentEditActivity : Activity() {

    private var valid: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_edit)

        val urlEditText = findViewById<EditText>(R.id.dz6UrlEditText)
        val nameEditText = findViewById<EditText>(R.id.dz6NameEditText)
        val ageEditText = findViewById<EditText>(R.id.dz6AgeEditText)

        val studentId = intent.getStringExtra(Dz6StudentListActivity.ID_EXTRA)

        if (studentId.isNotEmpty()) {
            val student = SingletonStudent.findStudentById(studentId)
            if (student != null) {
                urlEditText.setText(student.imageUrl)
                nameEditText.setText(student.name)
                ageEditText.setText(student.age.toString())
            }
        }

        findViewById<Button>(R.id.dz6SaveButton).setOnClickListener {
            val url = urlEditText.text.toString().trim()
            val name = nameEditText.text.toString().trim()
            val age = ageEditText.text.toString().toIntOrNull()

            if (TextUtils.isEmpty(url)) {
                urlEditText.setError("This field is Required")
                valid = false
            } else {
                urlEditText.setError(null)
            }

            if (TextUtils.isEmpty(name)) {
                nameEditText.setError("This field is Required")
                valid = false
            } else {
                nameEditText.setError(null)
            }

            if (age == null) {
                ageEditText.setError("This field is Required")
                valid = false
            } else {
                ageEditText.setError(null)
            }

            if (studentId.isNotEmpty()) {
                SingletonStudent.upgradeStudentById(studentId, url, age!!, name)
            } else {
                SingletonStudent.addNewStudent(url, age!!, name)
            }
            onBackPressed()
        }
    }
}