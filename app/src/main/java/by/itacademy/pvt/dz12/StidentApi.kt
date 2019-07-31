package by.itacademy.pvt.dz12

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface StidentApi {

    @GET("data/student")
    fun getStudents(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int
    ): Observable<MutableList<Student>>
}