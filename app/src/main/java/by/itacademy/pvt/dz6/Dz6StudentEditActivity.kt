package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Button
import by.itacademy.pvt.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

private const val STUDENT_ID = "STUDENT_ID"

class Dz6StudentEditActivity : Activity() {

    companion object {
        fun getIntent(context: Context, id: String = ""): Intent {
            val intent = Intent(context, Dz6StudentEditActivity::class.java)
            intent.putExtra(STUDENT_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_edit)

        val urlEditText = findViewById<TextInputEditText>(R.id.dz6UrlEditText)
        val nameEditText = findViewById<TextInputEditText>(R.id.dz6NameEditText)
        val ageEditText = findViewById<TextInputEditText>(R.id.dz6AgeEditText)

        val urlLinearLayout = findViewById<TextInputLayout>(R.id.dz6UrlLinearLayout)
        val nameLinearLayout = findViewById<TextInputLayout>(R.id.dz6NameLinearLayout)
        val ageLinearLayout = findViewById<TextInputLayout>(R.id.dz6AgeLinearLayout)

        val errorCannotBeBlank = resources.getString(R.string.dz6_error_cannot_be_blank)
        val errorUrlIsInvalid = resources.getString(R.string.dz6_error_url_is_invalid)

        val studentId = intent.getStringExtra(STUDENT_ID)
        val student: Student? = studentId.let { SupervisingStudents.findStudentById(it) }

            if (student != null) {
                urlEditText.setText(student.imageUrl)
                nameEditText.setText(student.name)
                ageEditText.setText(student.age.toString())
            }

        findViewById<Button>(R.id.dz6SaveButton).setOnClickListener {
            val url = urlEditText.text.toString().trim()
            val name = nameEditText.text.toString().trim()
            val age = ageEditText.text.toString().toIntOrNull()
            var isNotValid = false

            if (url.isEmpty()) {
                isNotValid = true
                urlLinearLayout.error = errorCannotBeBlank
            } else if (!URLUtil.isValidUrl(url)) {
                isNotValid = true
                urlLinearLayout.error = errorUrlIsInvalid
            } else {
                urlLinearLayout.error = null
                urlLinearLayout.isErrorEnabled = false
            }

            if (name.isEmpty()) {
                isNotValid = true
                nameLinearLayout.error = errorCannotBeBlank
            } else {
                nameLinearLayout.error = null
                nameLinearLayout.isErrorEnabled = false
            }

            if (age == null) {
                isNotValid = true
                ageLinearLayout.error = errorCannotBeBlank
            } else {
                ageLinearLayout.error = null
                ageLinearLayout.isErrorEnabled = false
            }

            if (isNotValid) {
                return@setOnClickListener
            }

            if (student == null) {
                SupervisingStudents.upgradeStudentById(Student(UUID.randomUUID().toString(), name, age!!, url))
            } else {
                SupervisingStudents.addNewStudent(Student(student.id, name, age!!, url))
            }
            onBackPressed()
        }
    }
}