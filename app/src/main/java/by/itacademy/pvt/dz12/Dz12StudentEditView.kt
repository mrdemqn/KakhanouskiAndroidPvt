package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.Student

interface Dz12StudentEditView : Dz12BaseView {
    fun showStudent(student: Student?)
    fun showError(error: String)
    fun back()
    fun end()
    fun showProgressBar()
    fun goneProgressBar()
}