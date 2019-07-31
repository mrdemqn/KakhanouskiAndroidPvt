package by.itacademy.pvt.dz11.mvp

interface Dz12DetailsPresenter : Dz11BasePresenter {
    fun getStudentById(id: String)
    fun deleteById(id: String)
}