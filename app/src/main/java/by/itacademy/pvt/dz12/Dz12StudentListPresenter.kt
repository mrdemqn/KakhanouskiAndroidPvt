package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.SupervisingStudents

class Dz12StudentListPresenter : Dz12ListPresenter {
    private var view: Dz12StudentListView? = null

    override fun setView(view: Dz12BaseView) {
        this.view = view as Dz12StudentListView
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