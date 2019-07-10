package by.itacademy.pvt.dz8

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.SupervisingStudents
import by.itacademy.pvt.utils.loadCircleImage

private const val ID_KEY = "ID_KEY"
private const val STUDENT_ID = "STUDENT_ID"

class Dz8StudentDetailsFragment : Fragment() {

    private var listener: Listener? = null

    companion object {
        val TAG  = Dz8StudentDetailsFragment::class.java.canonicalName!!

        fun getInstance(id: String): Dz8StudentDetailsFragment {
            val fragment = Dz8StudentDetailsFragment()

            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            fragment.arguments
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val errorId = resources.getString(R.string.dz6_error_id)

        val studentId = arguments?.getString(ID_KEY, null)
        val student = studentId?.let { SupervisingStudents.findStudentById(it) }
        val avatarImageView = view.findViewById<ImageView>(R.id.dz6AvatarImageView)

        if (student != null) {
            context?.let { loadCircleImage(it, student.imageUrl, avatarImageView) }
            view.findViewById<TextView>(R.id.dz6NameTextView).text = student.name
            view.findViewById<TextView>(R.id.dz6AgeTextView).text = student.age.toString()
        }

        view.findViewById<Button>(R.id.deleteButton).setOnClickListener {
            if (student == null) {
                Toast.makeText(context, errorId, Toast.LENGTH_SHORT).show()
            } else {
                SupervisingStudents.deleteStudentByIdFromList(studentId)
            }
            listener?.onClickedDeleteStudent()
        }

        view.findViewById<Button>(R.id.editButton).setOnClickListener {
            if (student == null) {
                Toast.makeText(context, errorId, Toast.LENGTH_SHORT).show()
            } else {
                listener?.onClickedStudentEdit(studentId)
            }
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
        fun onClickedStudentEdit(id: String)
        fun onClickedDeleteStudent()
    }
}