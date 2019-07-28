package by.itacademy.pvt.dz9

import by.itacademy.pvt.dz9.entity.Poi
import by.itacademy.pvt.dz9.entity.CoordinateParams

interface CarRepository {

    fun getCarByCoordinate(params: CoordinateParams, listener: CarRepositoryResult) //Добавить листенер на примере ресайклВью
}

interface CarRepositoryResult {
    fun onSuccess(data: List<Poi>)
    fun onError(throwable: Throwable)
}