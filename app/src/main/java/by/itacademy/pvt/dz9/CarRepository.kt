package by.itacademy.pvt.dz9

import by.itacademy.pvt.dz9.entity.Poi
import by.itacademy.pvt.dz9.entity.CoordinateParams
import io.reactivex.Single
import by.itacademy.pvt.dz9.entity.CarResponse

interface CarRepository {

    fun getCarByCoordinate(params: CoordinateParams, listener: CarRepositoryResult)
    fun getCar(params: CoordinateParams) : Single<CarResponse>
}

interface CarRepositoryResult {
    fun onSuccess(data: List<Poi>)
    fun onError(throwable: Throwable)
}