package by.itacademy.pvt.dz9

import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.CarResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// CRUD - create, read, update, delete
class CarRepositoryRemote(private val api: Api) : CarRepository {
    override fun getCarByCoordinate(params: CoordinateParams): Single<CarResponse> {
        return api.getCarsByCoordinate(
            params.coordinate1.latitude,
            params.coordinate1.longitude,
            params.coordinate2.latitude,
            params.coordinate2.longitude)
    }
}