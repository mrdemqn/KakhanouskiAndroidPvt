package by.itacademy.pvt.dz11.mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import androidx.fragment.app.Fragment
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.dz6.SupervisingStudents
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.UUID

private const val ID_KEY = "ID_KEY"

class Dz8StudentEditFragment : Fragment() {

    private var listener: Listener? = null

    companion object {
        val TAG = Dz8StudentEditFragment::class.java.canonicalName!!

        fun getInstance(id: String? = ID_KEY): Dz8StudentEditFragment {
            val fragment = Dz8StudentEditFragment()

            if (id != null) {
                val bundle = Bundle()
                bundle.putString(ID_KEY, id)
                fragment.arguments = bundle
            }
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val urlEditText = view.findViewById<TextInputEditText>(R.id.dz8UrlEditText)
        val nameEditText = view.findViewById<TextInputEditText>(R.id.dz8NameEditText)
        val ageEditText = view.findViewById<TextInputEditText>(R.id.dz8AgeEditText)

        val urlLinearLayout = view.findViewById<TextInputLayout>(R.id.dz8UrlLinearLayout)
        val nameLinearLayout = view.findViewById<TextInputLayout>(R.id.dz8NameLinearLayout)
        val ageLinearLayout = view.findViewById<TextInputLayout>(R.id.dz8AgeLinearLayout)

        val errorCannotBeBlank = resources.getString(R.string.dz6_error_cannot_be_blank)
        val errorUrlIsInvalid = resources.getString(R.string.dz6_error_url_is_invalid)

        val studentId = arguments?.getString(ID_KEY, null)
        val student: Student? = studentId?.let { SupervisingStudents.findStudentById(it) }
        val errorId = resources.getString(R.string.dz6_error_id)

        if (student != null) {
            urlEditText.setText(student.imageUrl)
            nameEditText.setText(student.name)
            ageEditText.setText(student.age.toString())
        }

        view.findViewById<Button>(R.id.dz8SaveButton).setOnClickListener {
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
                SupervisingStudents.addNewStudent(Student(UUID.randomUUID().toString(), name, age!!, url))
            } else {
                SupervisingStudents.upgradeStudentById(Student(student.id, name, age!!, url))
            }
            listener?.onClickedSaveStudent()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface Listener {
        fun onClickedSaveStudent()
    }
}