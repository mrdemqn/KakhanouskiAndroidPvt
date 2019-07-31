package by.itacademy.pvt.dz12

import io.reactivex.Observable

interface StudentRepository {

    fun get(pageSize: Int, offset: Int) : Observable<MutableList<Student>>

    fun getById(id: String) : Observable<Student>

    fun createOrUpdate(student: Student) : Observable<Boolean>

    fun delete(id: String) : Observable<Boolean>
}