package by.itacademy.pvt.dz11.mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.utils.loadCircleImage

private const val ID_KEY = "ID_KEY"

class Dz11StudentDetailsFragment : Fragment(), Dz11StudentDetailsView {

    private var listener: Listener? = null

    private lateinit var detailsPresenter: Dz11DetailsPresenter

    private var studentId: String? = null

    private lateinit var avatarImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var ageTextView: TextView

    companion object {
        val TAG  = Dz11StudentDetailsFragment::class.java.canonicalName!!

        fun getInstance(id: String): Dz11StudentDetailsFragment {
            val fragment = Dz11StudentDetailsFragment()

            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailsPresenter = Dz11StudentDetailsPresenter()
        detailsPresenter.setView(this)

        studentId = arguments?.getString(ID_KEY)

        avatarImageView = view.findViewById(R.id.dz8AvatarImageView)
        nameTextView = view.findViewById(R.id.dz8NameTextView)
        ageTextView = view.findViewById(R.id.dz8AgeTextView)

        studentId?.apply { detailsPresenter.getStudentById(this) }

        view.findViewById<Button>(R.id.dz8deleteButton).setOnClickListener {
            studentId?.apply {
                detailsPresenter.deleteById(this)
                listener?.onClickedDeleteStudent()
            }
        }

        view.findViewById<Button>(R.id.dz8editButton).setOnClickListener {
            studentId?.apply {
                listener?.onClickedStudentEdit(this)
            }
        }
    }

    override fun showStudent(student: Student?) {
        if (student == null) {
            listener?.completeFragmentWithAnError()
        } else {
            context?.let { loadCircleImage(it, student.imageUrl, avatarImageView) }
            ageTextView.text = student.age.toString()
            nameTextView.text = student.name
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

    override fun onDestroyView() {
        detailsPresenter.viewDestroyed()
        super.onDestroyView()
    }

    interface Listener {
        fun onClickedStudentEdit(id: String)
        fun onClickedDeleteStudent()
        fun completeFragmentWithAnError()
    }
}