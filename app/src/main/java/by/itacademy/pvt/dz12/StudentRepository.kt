package by.itacademy.pvt.dz12

import io.reactivex.Completable
import io.reactivex.Observable
import by.itacademy.pvt.dz6.Student

interface StudentRepository {

    fun get(pageSize: Int, offset: Int): Observable<List<Student>>

    fun getByName(name: String, pageSize: Int, offset: Int): Observable<List<Student>>

    fun getById(id: String): Observable<Student>

    fun delete(id: String): Completable

    fun create(student: Student): Completable

    fun update(student: Student): Completable
}