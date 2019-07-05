package by.itacademy.pvt.dz8

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.utils.AppPrefManager
import java.util.*
import kotlin.concurrent.schedule

class Dz8StudentListActivity : Activity(), Dz8ListAdapter.ClickListener {

    private lateinit var adapterDz8List: Dz8ListAdapter
    private var searchText: String = ""

    private lateinit var dz8EditText: EditText
    private lateinit var sharPrefManager: AppPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8_student_list)

        sharPrefManager = AppPrefManager(this)

        dz8EditText = findViewById(R.id.dz8SearchEditText)

        dz8EditText.setText(sharPrefManager.getUserText())

        val recyclerView = findViewById<RecyclerView>(R.id.dz8StListRecycleView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapterDz8List = Dz8ListAdapter(SupervisingStudents.getStudents(), this)
        recyclerView.adapter = adapterDz8List

        val searchEditText = findViewById<EditText>(R.id.dz8SearchEditText)
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
                    runOnUiThread {
                        executeSearch()
                    }
                }
            }
        })

        findViewById<ImageView>(R.id.dz8AddStudent).setOnClickListener {
            startActivity(Dz8StudentEditActivity.getIntent(this@Dz8StudentListActivity, ""))
        }
    }

    override fun onStart() {
        super.onStart()
        this.dz8EditText.setText(sharPrefManager.getUserText())
    }

    override fun onStop() {
        super.onStop()

       sharPrefManager.saveUserText(dz8EditText.text.toString())
    }

    override fun onStudentClick(item: Student) {
        startActivity(Dz8StudentDetailsActivity.getIntent(this@Dz8StudentListActivity, item.id))
    }

    override fun onResume() {
        super.onResume()
        executeSearch()
    }

    private fun executeSearch() {
        adapterDz8List.updateList(SupervisingStudents.searchByName(searchText))
    }
}