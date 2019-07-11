package by.itacademy.pvt.dz9

import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.Poi

interface CarRepository {

    fun getCarByCoordinate(params: CoordinateParams, listener: CarRepositoryResult) //Добавить листенер на примере ресайклВью
}

interface CarRepositoryResult {
    fun onSuccess(list: List<Poi>)
    fun onError(throwable: Throwable)
}