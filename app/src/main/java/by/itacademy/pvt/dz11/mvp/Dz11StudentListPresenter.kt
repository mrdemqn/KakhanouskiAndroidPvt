package by.itacademy.pvt.dz11.mvp

import by.itacademy.pvt.dz6.SupervisingStudents

class Dz11StudentListPresenter : Dz11ListPresenter {
    private var view: Dz11StudentListView? = null

    override fun setView(view: Dz11BaseView) {
        this.view = view as Dz11StudentListView
    }

    override fun viewDestroyed() {
        this.view = null
    }

    override fun searchByName(name: String) {
        view?.showSearchResults(SupervisingStudents.searchByName(name))
    }

    override fun getStudentList() {
        view?.showStudentsList(SupervisingStudents.getStudents())
    }
}