package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.dz9.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

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

    override fun addNewStudent(url: String, name: String, age: Int) {
        disposable = repository
            .create(
                Student(
                    id = UUID.randomUUID().toString(),
                    imageUrl = url,
                    name = name,
                    age = age
                )
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.end()
            }, { throwable ->
                view?.showError(throwable.message.toString())
            })
    }

    override fun upgradeStudent(id: String, name: String, age: Int, url: String) {
        disposable = repository
            .update(
                Student(
                    id = id,
                    imageUrl = url,
                    name = name,
                    age = age
                )
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.end()
            }, { throwable ->
                view?.showError(throwable.message.toString())
            })
    }

    override fun getById(id: String) {
        disposable = id.let { studentId ->
            repository
                .getById(studentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ getById ->
                    view?.showStudent(getById)
                }, { throwable ->
                    view?.showError(throwable.message.toString())
                })
        }
    }
}