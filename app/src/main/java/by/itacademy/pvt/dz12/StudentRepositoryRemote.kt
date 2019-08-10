package by.itacademy.pvt.dz12

import io.reactivex.Completable
import io.reactivex.Observable
import by.itacademy.pvt.dz6.Student

class StudentRepositoryRemote(private val studentApi: StudentApi): StudentRepository {

    override fun get(pageSize: Int, offset: Int): Observable<List<Student>> {
        return studentApi.get(pageSize, offset)
    }

    override fun getByName(name: String, pageSize: Int, offset: Int): Observable<List<Student>> {
        return studentApi.getByName(pageSize, offset, "name LIKE '%$name%'")
    }

    override fun getById(id: String): Observable<Student> {
        return studentApi.getById(id)
    }
  
    override fun delete(id: String): Completable {
        return studentApi.delete(id)
    }
  
    override fun create(student: Student): Completable {
        return studentApi.create(student)
    }

    override fun update(student: Student): Completable {
        return studentApi.update(student.id, student)
    }
}