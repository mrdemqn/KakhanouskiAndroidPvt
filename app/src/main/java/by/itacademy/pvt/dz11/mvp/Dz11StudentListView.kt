package by.itacademy.pvt.dz11.mvp

import by.itacademy.pvt.dz6.Student

interface Dz11StudentListView : Dz11BaseView {
    fun showSearchResults(list: List<Student>)
    fun showStudentsList(list: List<Student>)
}