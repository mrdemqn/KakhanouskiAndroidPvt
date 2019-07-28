package by.itacademy.pvt.dz11.mvp

import by.itacademy.pvt.dz6.SupervisingStudents
import by.itacademy.pvt.dz6.Student
import java.util.*

class Dz11StudentEditPresenter : Dz11EditPresenter {
    private var view: Dz11StudentEditView? = null

    override fun setView(view: Dz11BaseView) {
        this.view = view as Dz11StudentEditView
    }

    override fun viewDestroyed() {
        this.view = null
    }

    override fun addNewStudent(name: String, age: Int, url: String) {
        SupervisingStudents.addNewStudent(Student(UUID.randomUUID().toString(), name, age, url))
    }

    override fun upgradeStudent(id: String, name: String, age: Int, url: String) {
        SupervisingStudents.upgradeStudentById(Student(id, name, age, url))
    }

    override fun getStudentById(id: String) {
        val student: Student? = SupervisingStudents.findStudentById(id)
        view?.showStudent(student)
    }
}
