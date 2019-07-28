package by.itacademy.pvt.dz11.mvp

interface Dz11ListPresenter : Dz11BasePresenter {
    fun getStudentList()
    fun searchByName(name: String)
}