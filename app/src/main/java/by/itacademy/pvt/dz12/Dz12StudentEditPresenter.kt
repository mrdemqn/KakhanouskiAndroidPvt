package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.SupervisingStudents
import by.itacademy.pvt.dz6.Student
import java.util.*

class Dz12StudentEditPresenter : Dz12EditPresenter {
    private var view: Dz12StudentEditView? = null

    override fun setView(view: Dz12BaseView) {
        this.view = view as Dz12StudentEditView
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
