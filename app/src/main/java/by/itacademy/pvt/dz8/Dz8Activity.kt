package by.itacademy.pvt.dz8

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.SupervisingStudents

class Dz8Activity : FragmentActivity(), Dz8StudentEditFragment.Listener, Dz8StudentListFragment.Listener,
    Dz8StudentDetailsFragment.Listener {

    private var isTabletMode: Boolean = false

    private val d8Conteiner1 = R.id.dz8Conteiner1
    private val d8Conteiner2 = R.id.dz8Conteiner2

    private val transaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)

        if (savedInstanceState == null) {
            createTransaction(R.id.dz8Conteiner1, Dz8StudentListFragment())
        }
        isTabletMode = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    }

    private fun choiseOrientation(isTabletMode: Boolean): Int {
        val id: Int
        if (isTabletMode) {
            id = d8Conteiner2
        } else id = d8Conteiner1
        return id
    }

    private fun createTransaction(idContainer: Int, fragment: Fragment) {
        transaction.replace(idContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onClickedSaveStudent() {
        createTransaction(R.id.dz8Conteiner1, Dz8StudentListFragment())
    }

    override fun clickOnAddStudent() {
        createTransaction(R.id.dz8Conteiner2, Dz8StudentEditFragment())
    }

    override fun onStudentClicked(id: String) {
        createTransaction(choiseOrientation(isTabletMode), Dz8StudentDetailsFragment.getInstance(id))
    }

    override fun onClickedStudentEdit(id: String) {
        createTransaction(choiseOrientation(isTabletMode), Dz8StudentEditFragment.getInstance(id))
    }

    override fun onClickedDeleteStudent(id: String) {
        SupervisingStudents.deleteStudentByIdFromList(id)
        transaction.replace(R.id.dz8Conteiner1, Dz8StudentListFragment())
        supportFragmentManager.popBackStack()
        transaction.commit()
    }
}