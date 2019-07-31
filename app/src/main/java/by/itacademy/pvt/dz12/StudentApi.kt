package by.itacademy.pvt.dz12

import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface StudentApi {

    @GET("data/student")
    fun getStudents(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int
    ): Observable<MutableList<Student>>

    @GET("data/student")
    fun searchByName(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int,
        @Query("where") state: String
    ): Observable<MutableList<Student>>

    @GET("data/student/{id}")
    fun getById(
        @Path("id") id: String
    ): Observable<Student>

    @POST("data/student")
    fun create(
        @Body student: Student
    ): Completable

    @PUT("data/student/{id}")
    fun upgrade(
        @Path("id") id: String,
        @Body student: Student
    ): Completable

    @DELETE("data/student/{id}")
    fun delete(
        @Path("id") id: String
    ) : Completable
}