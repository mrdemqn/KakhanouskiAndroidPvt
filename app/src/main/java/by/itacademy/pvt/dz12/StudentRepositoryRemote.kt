package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz6.Student
import io.reactivex.Completable
import io.reactivex.Observable

class StudentRepositoryRemote(private val studentApi: StudentApi): StudentRepository {

    override fun get(pageSize: Int, offset: Int): Observable<MutableList<Student>> {
        return studentApi.getStudents(pageSize, offset)
    }

    override fun getById(id: String): Observable<Student> {
        return studentApi.getById(id)
    }

    override fun create(student: Student): Completable {
        return studentApi.create(student)
    }

    override fun upgrade(student: Student): Completable {
        return studentApi.upgrade(student.id, student)
    }

    override fun delete(id: String): Completable {
        return studentApi.delete(id)
    }

    override fun searchByName(name: String, pageSize: Int, offset: Int): Observable<MutableList<Student>> {
        return studentApi.searchByName(pageSize, offset, "name LIKE '%$name%'")
    }
}