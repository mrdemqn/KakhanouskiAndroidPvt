package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz11.mvp.Dz11BaseView
import by.itacademy.pvt.dz11.mvp.Dz12DetailsPresenter
import by.itacademy.pvt.dz6.SupervisingStudents
import by.itacademy.pvt.dz6.Student

class Dz12StudentDetailsPresenter : Dz12DetailsPresenter {

    private var view: Dz12StudentDetailsView? = null

    override fun setView(view: Dz11BaseView) {
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