package by.itacademy.pvt.dz12

interface Dz12EditPresenter : Dz12BasePresenter {
    fun getById(id: String)
    fun addNewStudent(name: String, age: Int, url: String)
    fun upgradeStudent(id: String, name: String, age: Int, url: String)
}