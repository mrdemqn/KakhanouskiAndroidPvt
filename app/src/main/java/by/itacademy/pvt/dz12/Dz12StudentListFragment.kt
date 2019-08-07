package by.itacademy.pvt.dz12

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6ListAdapter
import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.dz6.SupervisingStudents
import by.itacademy.pvt.utils.AppPrefManager
import java.util.*
import kotlin.concurrent.schedule

private const val ID_KEY = "ID_KEY"

class Dz12StudentListFragment : Fragment(), Dz6ListAdapter.ClickListener, Dz12StudentListView {

    private var listener: Listener? = null

    private lateinit var adapterDz8List: Dz6ListAdapter
    private var searchText: String = ""

    private lateinit var dz8EditText: EditText
    private lateinit var sharPrefManager: AppPrefManager
    private lateinit var listPresenter: Dz12ListPresenter
    private lateinit var progressBar: ProgressBar

    companion object {
        val TAG = Dz12StudentListFragment::class.java.canonicalName!!

        fun getInstance(id: String? = null): Dz12StudentListFragment {
            val fragment = Dz12StudentListFragment()

            if (id != null) {
                val bundle = Bundle()
                bundle.putString(ID_KEY, id)
                fragment.arguments = bundle
            }
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharPrefManager = AppPrefManager(view.context)
        dz8EditText = view.findViewById(R.id.dz6SearchEditText)
        dz8EditText.setText(sharPrefManager.getUserText())

        listPresenter = Dz12StudentListPresenter()
        listPresenter.setView(this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.dz6StListRecycleView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        adapterDz8List = Dz6ListAdapter(SupervisingStudents.getStudents(), this)
        recyclerView.adapter = adapterDz8List

        val searchEditText = view.findViewById<EditText>(R.id.dz6SearchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            private var timer = Timer()
            private val delay = 350L

            override fun beforeTextChanged(sequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(sequence: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(delay) {
                    searchText = sequence.toString()
                    activity?.runOnUiThread {
                        executeSearch()
                    }
                }
            }
        })
        view.findViewById<ImageView>(R.id.dz6AddStudent).setOnClickListener {
            listener?.clickOnAddStudent()
        }
        listPresenter.getStudentList()
    }

    override fun showSearchResults(listStudent: MutableList<Student>) {
        adapterDz8List.updateList(listStudent)
    }

    override fun showStudentsList(listStudent: MutableList<Student>) {
        adapterDz8List.updateList(listStudent)
    }

    override fun onStart() {
        super.onStart()
        dz8EditText.setText(sharPrefManager.getUserText())

    }

    override fun onStop() {
        super.onStop()
        sharPrefManager.saveUserText(dz8EditText.text.toString())
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

    override fun onStudentClick(item: Student) {
        listener?.onStudentClicked(item.id)
    }

    override fun showError(error: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        executeSearch()
    }

    fun updateListRecycle() {
        executeSearch()
    }

    private fun executeSearch() {
        listPresenter.searchByName(searchText)
    }

    override fun onDestroyView() {
        listPresenter.viewDestroyed()
        super.onDestroyView()
    }

    interface Listener {
        fun clickOnAddStudent()
        fun onStudentClicked(id: String)
    }
}
