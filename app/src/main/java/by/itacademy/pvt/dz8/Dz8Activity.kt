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
            id = R.id.dz8Conteiner2
        } else id = R.id.dz8Conteiner1
        return id
    }

    private fun createTransaction(idContainer: Int, fragment: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
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
        if (isTabletMode) {
        createTransaction(R.id.dz8Conteiner2, Dz8StudentEditFragment.getInstance(id))
        } else {
            createTransaction(R.id.dz8Conteiner1, Dz8StudentEditFragment.getInstance(id))
        }
    }

    override fun onClickedDeleteStudent(id: String) {
        SupervisingStudents.deleteStudentByIdFromList(id)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.dz8Conteiner1, Dz8StudentListFragment())
        supportFragmentManager.popBackStack()
        transaction.commit()
    }
}