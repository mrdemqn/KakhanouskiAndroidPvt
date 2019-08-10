package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.Student

interface Dz12StudentListView : Dz12BaseView {
    fun showSearchResults(listStudent: MutableList<Student>)
    fun showStudentsList(listStudent: MutableList<Student>)
}