package by.itacademy.pvt.dz12

interface Dz12DetailsPresenter : Dz12BasePresenter {
    fun getStudentById(id: String)
    fun deleteById(id: String)
}