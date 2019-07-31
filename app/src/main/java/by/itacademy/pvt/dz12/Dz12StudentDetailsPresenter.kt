package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.SupervisingStudents
import by.itacademy.pvt.dz6.Student
import io.reactivex.disposables.Disposable

class Dz12StudentDetailsPresenter(private val id: String) : Dz12DetailsPresenter {

    private var view: Dz12StudentDetailsView? = null

    private var student: Student? = null
    private val repository =
    private var disposable: Disposable? = null

    override fun setView(view: Dz12BaseView) {
        this.view = view as Dz12StudentDetailsView
    }

    override fun viewDestroyed() {
        this.view = null
    }

    override fun getStudentById(id: String) {
        val student: Student? = SupervisingStudents.findStudentById(id)
        view?.showStudent(student)
    }

    override fun deleteById(id: String) {
        SupervisingStudents.deleteStudentByIdFromList(id)
    }
}