package by.itacademy.pvt.dz12

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Student
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

private const val ID_KEY = "ID_KEY"

class Dz12StudentEditFragment : Fragment(), Dz12StudentEditView {

    private var listener: Listener? = null
    private lateinit var editPresenter: Dz12EditPresenter

    private var studentId: String? = null

    private lateinit var urlEditText: TextInputEditText
    private lateinit var nameEditText: TextInputEditText
    private lateinit var ageEditText: TextInputEditText

    private lateinit var urlLinearLayout: TextInputLayout
    private lateinit var nameLinearLayout: TextInputLayout
    private lateinit var ageLinearLayout: TextInputLayout

    companion object {
        val TAG = Dz12StudentEditFragment::class.java.canonicalName!!

        fun getInstance(id: String? = null): Dz12StudentEditFragment {
            val fragment = Dz12StudentEditFragment()

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
        editPresenter = Dz12StudentEditPresenter()
        editPresenter.setView(this)

        urlEditText = view.findViewById(R.id.dz8UrlEditText)
        nameEditText = view.findViewById(R.id.dz8NameEditText)
        ageEditText = view.findViewById(R.id.dz8AgeEditText)

        urlLinearLayout = view.findViewById(R.id.dz8UrlLinearLayout)
        nameLinearLayout = view.findViewById(R.id.dz8NameLinearLayout)
        ageLinearLayout = view.findViewById(R.id.dz8AgeLinearLayout)

        studentId = arguments?.getString(ID_KEY, null)
        studentId?.apply { editPresenter.getStudentById(this) }
        val errorId = resources.getString(R.string.dz6_error_id)

        view.findViewById<View>(R.id.dz8SaveButton).setOnClickListener {
            saveUpgrate()
        }
    }

    override fun showStudent(student: Student?) {
        if (student != null) {
            Toast.makeText(context, "Student not found", Toast.LENGTH_SHORT).show()
        } else {
            urlEditText.setText(student?.imageUrl)
            nameEditText.setText(student?.name)
            ageEditText.setText(student?.age.toString())
        }
    }

    fun saveUpgrate() {
        val errorCannotBeBlank = resources.getString(R.string.dz6_error_cannot_be_blank)
        val errorUrlIsInvalid = resources.getString(R.string.dz6_error_url_is_invalid)

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
            return
        }

        if (studentId == null) {
            editPresenter.addNewStudent(name, age!!, url)
        } else {
            editPresenter.upgradeStudent(studentId!!, name, age!!, url)
        }
        listener?.onClickedSaveStudent()
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