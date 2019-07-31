package by.itacademy.pvt.dz9

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import by.itacademy.pvt.dz9.entity.CarResponse
import io.reactivex.Single

interface Api {

    @GET("t/fake-api/car-service.php")
    fun getCarsByCoordinate(
        @Query("p1Lat") p1Lat: Double,
        @Query("p1Lon") p1Lon: Double,
        @Query("p2Lat") p2Lat: Double,
        @Query("p2Lon") p2Lon: Double
    ): Call<CarResponse>

    fun getCar(
        @Query("p1Lat") p1Lat: Double,
        @Query("p1Lon") p1Lon: Double,
        @Query("p2Lat") p2Lat: Double,
        @Query("p2Lon") p2Lon: Double
    ): Single<CarResponse>
}