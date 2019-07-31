package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.Student

interface Dz12EditPresenter : Dz12BasePresenter {
    fun getStudentById(id: String)
    fun addNewStudent(id: String, name: String, age: Int, url: String)
    fun upgradeStudent(id: String, name: String, age: Int, url: String)
}