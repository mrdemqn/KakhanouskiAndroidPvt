package by.itacademy.pvt.dz12

import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*
import by.itacademy.pvt.dz6.Student

interface StudentApi {

    @GET("data/student")
    fun get(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int
    ): Observable<List<Student>>

    @GET("data/student")
    fun getByName(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int,
        @Query("where") condition: String
    ): Observable<List<Student>>

    @GET("data/student/{id}")
    fun getById(
        @Path("id") id: String
    ): Observable<Student>

    @DELETE("data/student/{id}")
    fun delete(
        @Path("id") id: String
    ): Completable

    @PUT("data/student/{id}")
    fun update(
        @Path("id") id: String,
        @Body student: Student
    ): Completable

    @POST("data/student")
    fun create(
        @Body student: Student
    ): Completable
}