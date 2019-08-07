package by.itacademy.pvt.dz12

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.utils.loadCircleImage

private const val ID_KEY = "ID_KEY"

class Dz12StudentDetailsFragment : Fragment(), Dz12StudentDetailsView {

    private var listener: Listener? = null

    private lateinit var detailsPresenter: Dz12DetailsPresenter

    private lateinit var avatarImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var studentDetails: ScrollView
    private val errorId = resources.getString(R.string.dz6_error_id)

    companion object {
        val TAG  = Dz12StudentDetailsFragment::class.java.canonicalName!!

        fun getInstance(id: String): Dz12StudentDetailsFragment {
            val fragment = Dz12StudentDetailsFragment()

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


        val studentId = arguments?.getString(ID_KEY, null)

        detailsPresenter = Dz12StudentDetailsPresenter()
        detailsPresenter.setView(this)

        avatarImageView = view.findViewById(R.id.dz8AvatarImageView)
        nameTextView = view.findViewById(R.id.dz8NameTextView)
        ageTextView = view.findViewById(R.id.dz8AgeTextView)

        studentId?.apply { detailsPresenter.getById(this) }

        view.findViewById<Button>(R.id.dz8deleteButton).setOnClickListener {
            studentId?.apply {
                progressBar.visibility = View.VISIBLE
                detailsPresenter.deleteById(this)
            }
        }

        view.findViewById<Button>(R.id.dz8editButton).setOnClickListener {
            studentId?.apply {
                listener?.onClickedStudentEdit(this)
            }
        }
    }



    override fun showStudent(student: Student?) {
        progressBar.visibility = View.GONE
        if (student == null) {
           showError(errorId)
        } else {
            context?.let { loadCircleImage(student.imageUrl, avatarImageView) }
            ageTextView.text = student.age.toString()
            nameTextView.text= student.name
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onDestroyView() {
        detailsPresenter.viewDestroyed()
        super.onDestroyView()
    }

    override fun end() {
        listener?.deleted()
    }

    override fun showError(error: String) {
        listener?.completeFragmentWithAnError(error)
    }

    interface Listener {
        fun deleted()
        fun onClickedStudentEdit(id: String)
        fun completeFragmentWithAnError(error: String)
    }
}