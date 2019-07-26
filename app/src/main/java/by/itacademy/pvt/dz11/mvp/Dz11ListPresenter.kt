package by.itacademy.pvt.dz11.mvp

interface Dz11ListPresenter : Dz11BasePresenter {
    fun getStudentsList()
    fun searchByName(name: String)
}