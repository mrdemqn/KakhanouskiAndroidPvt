package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.SupervisingStudents
import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.dz9.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12StudentEditPresenter : Dz12EditPresenter {
    private var view: Dz12StudentEditView? = null

    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    override fun setView(view: Dz12BaseView) {
        this.view = view as Dz12StudentEditView
    }

    override fun viewDestroyed() {
        disposable?.dispose()
        this.view = null
    }

    override fun addNewStudent(id: String, name: String, age: Int, url: String) {
        view?.showProgressBar()
        disposable = repository
            .getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                upgradeStudent(
                    Dz6Student(
                        id = idStudent,
                        imageUrl = url,
                        name = name,
                        age = age.toInt()
                    )
                )
            }, {
                insertStudent(
                    Dz6Student(
                        id = System.currentTimeMillis().toString(),
                        imageUrl = url,
                        name = name,
                        age = age.toInt()
                    )
                )
            })
    }

    override fun upgradeStudent(id: String, name: String, age: Int, url: String) {
        SupervisingStudents.upgradeStudentById(Student(id, name, age, url))
    }

    override fun getStudentById(id: String) {
        disposable =
            repository
                .getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ itStudent ->
                    view?.goneProgressBar()
                    view?.showStudent(itStudent)
                }, { throwable ->
                    view?.goneProgressBar()
                })
    }
}