package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.Student
import io.reactivex.disposables.Disposable
import by.itacademy.pvt.dz9.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Dz12StudentDetailsPresenter : Dz12DetailsPresenter {

    private var view: Dz12StudentDetailsView? = null

    private var student: Student? = null
    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    override fun setView(view: Dz12BaseView) {
        this.view = view as Dz12StudentDetailsView
    }

    override fun viewDestroyed() {
        disposable?.dispose()
        this.view = null
    }

    override fun getById(id: String) {
        disposable = repository
            .getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({itStudent ->
                student = itStudent
                view?.showStudent(student)
            }, { error ->
                view?.showError(error.toString())
            })
    }

    override fun deleteById(id: String) {
        view?.showProgressBar()
        disposable = repository
            .delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.end()
            }, { error ->
                view?.showError(error.toString())
            })
    }
}