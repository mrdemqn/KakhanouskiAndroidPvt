package by.itacademy.pvt.dz6

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import java.util.*
import kotlin.concurrent.schedule

class Dz6StudentListActivity : Activity(), Dz6ListAdapter.ClickListener {

    private lateinit var adapterDz6List: Dz6ListAdapter
    private var searchText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_list)

        val recyclerView = findViewById<RecyclerView>(R.id.dz6StListRecycleView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapterDz6List = Dz6ListAdapter(SupervisingStudents.getStudents(), this)
        recyclerView.adapter = adapterDz6List

        val searchEditText = findViewById<EditText>(R.id.dz6SearchEditText)
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

        findViewById<ImageView>(R.id.dz6AddStudent).setOnClickListener {
            startActivity(Dz6StudentEditActivity.getIntent(this@Dz6StudentListActivity, ""))
        }
    }

    override fun onStudentClick(item: Student) {
        startActivity(Dz6StudentDetailsActivity.getIntent(this@Dz6StudentListActivity, item.id))
    }

    override fun onResume() {
        super.onResume()
        executeSearch()
    }

    private fun executeSearch() {
        adapterDz6List.updateList(SupervisingStudents.searchByName(searchText))
    }
}