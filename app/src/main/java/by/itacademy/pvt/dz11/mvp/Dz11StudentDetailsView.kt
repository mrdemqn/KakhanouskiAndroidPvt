package by.itacademy.pvt.dz11.mvp

import by.itacademy.pvt.dz6.Student

interface Dz11StudentDetailsView : Dz11BaseView {
    fun showStudent(student: Student?)
}