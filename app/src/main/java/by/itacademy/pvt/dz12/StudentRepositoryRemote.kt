package by.itacademy.pvt.dz12

import io.reactivex.Observable

class StudentRepositoryRemote(private val api: StidentApi): StudentRepository {

    override fun get(pageSize: Int, offset: Int): Observable<MutableList<Student>> {
        return api.getStudents(pageSize, offset)
    }

    override fun getById(id: String): Observable<Student> {

    }

    override fun createOrUpdate(student: Student): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}