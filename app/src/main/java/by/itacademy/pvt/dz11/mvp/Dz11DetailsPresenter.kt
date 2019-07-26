package by.itacademy.pvt.dz11.mvp

interface Dz11DetailsPresenter : Dz11BasePresenter {
    fun getStudentById(id: String)
    fun deleteStudentById(id: String)
}