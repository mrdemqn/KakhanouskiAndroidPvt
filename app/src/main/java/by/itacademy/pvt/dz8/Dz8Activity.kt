package by.itacademy.pvt.dz8

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R

class Dz8Activity : FragmentActivity(), Dz8StudentEditFragment.Listener, Dz8StudentListFragment.Listener,
    Dz8StudentDetailsFragment.Listener {

    private var isLandScape: Boolean = false

    private val containerDetails: Int
        get() {
            return if (isLandScape) {
                R.id.dz8Conteiner2
            } else {
                R.id.dz8Conteiner1
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)

        isLandScape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8Conteiner1, Dz8StudentListFragment(), Dz8StudentListFragment.TAG)
            transaction.commit()
        }
    }

    override fun onClickedSaveStudent() {
        val transaction = supportFragmentManager.beginTransaction()

        (supportFragmentManager.findFragmentByTag(Dz8StudentListFragment.TAG) as? Dz8StudentListFragment)?.updateListRecycle()

        if (isLandScape) {
            supportFragmentManager.findFragmentByTag(Dz8StudentEditFragment.TAG)?.apply {transaction.remove(this) }
        }
        transaction.commit()
    }

    override fun clickOnAddStudent() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerDetails, Dz8StudentEditFragment.getInstance(), Dz8StudentEditFragment.TAG)
        transaction.commit()
    }

    override fun onStudentClicked(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerDetails, Dz8StudentDetailsFragment.getInstance(id), Dz8StudentDetailsFragment.TAG)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onClickedStudentEdit(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerDetails, Dz8StudentEditFragment.getInstance(id), Dz8StudentEditFragment.TAG)
        transaction.commit()
    }

    override fun onClickedDeleteStudent() {
        val transaction = supportFragmentManager.beginTransaction()

        (supportFragmentManager.findFragmentByTag(Dz8StudentListFragment.TAG) as? Dz8StudentListFragment)?.updateListRecycle()

        if (isLandScape) {
            supportFragmentManager.findFragmentByTag(Dz8StudentDetailsFragment.TAG)?.apply { transaction.remove(this) }
            transaction.replace(R.id.dz8Conteiner2, Fragment())
        } else {
            supportFragmentManager.popBackStack()
        }
        transaction.commit()
    }
}