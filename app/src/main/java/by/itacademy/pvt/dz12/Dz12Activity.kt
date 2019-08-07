package by.itacademy.pvt.dz12

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R

class Dz12Activity : FragmentActivity(), Dz12StudentEditFragment.Listener, Dz12StudentListFragment.Listener,
    Dz12StudentDetailsFragment.Listener {


    private var isTabletMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)
        isTabletMode = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8Conteiner1, Dz12StudentListFragment())
            transaction.commit()
        }
    }

    private val choiseOrientation: Int
        get() {
            return if (isTabletMode) {
                R.id.dz8Conteiner2
            } else {
                R.id.dz8Conteiner1
            }
        }

    override fun onClickedSaveStudent() {
        val transaction = supportFragmentManager.beginTransaction()

        (supportFragmentManager.findFragmentByTag(Dz12StudentListFragment.TAG)
                as? Dz12StudentListFragment)?.updateListRecycle()

        if (isTabletMode) {
            supportFragmentManager.findFragmentByTag(Dz12StudentEditFragment.TAG)
                ?.apply { transaction.remove(this) }
        }
        transaction.commit()
    }

    override fun clickOnAddStudent() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(choiseOrientation, Dz12StudentEditFragment.getInstance())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun deleted() {
        val transaction = supportFragmentManager.beginTransaction()

        if (isTabletMode) {
            (supportFragmentManager.findFragmentByTag(Dz12StudentListFragment.TAG) as? Dz12StudentListFragment)?.updateListRecycle()
            supportFragmentManager.findFragmentByTag(Dz12StudentDetailsFragment.TAG)?.apply { transaction.remove(this) }
            transaction.replace(R.id.dz8Conteiner2, Fragment())
        } else {
            supportFragmentManager.popBackStack()
        }

        transaction.commit()
    }

    override fun onStudentClicked(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(choiseOrientation, Dz12StudentDetailsFragment.getInstance(id))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onClickedStudentEdit(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(choiseOrientation, Dz12StudentEditFragment.getInstance())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun completeFragmentWithAnError(error: String) {
        Toast.makeText(this, "Student nou found", Toast.LENGTH_SHORT).show()
        val transaction = supportFragmentManager.beginTransaction()
        if (isTabletMode) {
            supportFragmentManager.findFragmentByTag(Dz12StudentDetailsFragment.TAG)
                ?.apply { transaction.remove(this) }
            transaction.replace(R.id.dz8Conteiner2, Fragment())
        } else {
            supportFragmentManager.popBackStack()
        }
        transaction.commit()
    }
}