package by.itacademy.pvt.dz11.mvp

import by.itacademy.pvt.dz6.SupervisingStudents
import by.itacademy.pvt.dz6.Student

class Dz11StudentDetailsPresenter : Dz11DetailsPresenter {
    private var view: Dz11StudentDetailsView? = null

    override fun setView(view: Dz11BaseView) {
        this.view = view as Dz11StudentDetailsView
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