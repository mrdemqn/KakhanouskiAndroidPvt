package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.Student
import io.reactivex.Completable
import io.reactivex.Observable

interface StudentRepository {

    fun get(pageSize: Int, offset: Int) : Observable<MutableList<Student>>

    fun getById(id: String) : Observable<Student>

    fun create(student: Student) : Completable

    fun upgrade(student: Student) : Completable

    fun delete(id: String) : Completable

    fun searchByName(name: String, pageSize: Int, offset: Int): Observable<MutableList<Student>>
}