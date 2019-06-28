package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.itacademy.pvt.R


class Dz6StudentListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_list)

//        val searchEditText = findViewById<EditText>(R.id.searchEditText)
//

        findViewById<View>(R.id.dz6AddStudent).setOnClickListener {
            startStudentEditActivity()
        }
    }

    private fun startStudentEditActivity() {
        val intent = Intent(this, Dz6StudentEditActivity::class.java)
        startActivity(intent)
    }
}