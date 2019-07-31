package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.Student

interface Dz12StudentDetailsView : Dz12BaseView {
    fun showStudent(student: Student?)
    fun showProgressBar()
    fun end()
    fun showError(error: String)
    fun back()
}