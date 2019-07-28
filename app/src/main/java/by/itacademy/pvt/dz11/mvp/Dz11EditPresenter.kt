package by.itacademy.pvt.dz11.mvp

interface Dz11EditPresenter : Dz11BasePresenter {
    fun getStudentById(id: String)
    fun addNewStudent(name: String, age: Int, url: String)
    fun upgradeStudent(id: String, name: String, age: Int, url: String)
}