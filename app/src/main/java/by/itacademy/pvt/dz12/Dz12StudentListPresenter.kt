package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.dz9.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12StudentListPresenter : Dz12ListPresenter {
    private var view: Dz12StudentListView? = null

    private val pageSize = 10
    private val offset = 0
    private val repository = provideStudentRepository()
    private val listStudents: MutableList<Student> = mutableListOf()
    private var disposable: Disposable? = null
    private var searchText: String = ""
  
    override fun setView(view: Dz12BaseView) {
        this.view = view as Dz12StudentListView
    }

    override fun viewDestroyed() {
        disposable?.dispose()
        this.view = null
    }

    override fun searchByName(name: String) {
        searchText = name
        disposable = repository
            .getByName(name, pageSize, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listStudents.clear()
                listStudents.addAll(it)
                view?.showSearchResults(listStudents)
            }, { throwable ->
                view?.showError(throwable.message.toString())
            })
    }

    override fun getStudentList() {
        disposable = repository
            .get(pageSize, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({studentList ->
                listStudents.clear()
                listStudents.addAll(studentList)
                view?.showStudentsList(listStudents)
            }, { throwable ->
                view?.showError(throwable.message.toString())
            })
    }
}