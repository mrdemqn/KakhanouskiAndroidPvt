package by.itacademy.pvt.dz11.mvp

interface Dz11BasePresenter {
    fun onViewDestroyed()
    fun setView(view: Dz11BaseView)
}