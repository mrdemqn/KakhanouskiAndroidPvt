package by.itacademy.pvt.dz9

import android.view.View
import by.itacademy.pvt.dz9.entity.CoordinateParams

interface CarRepository {

    fun getCarByCoordinate(params: CoordinateParams, listener: View.OnClickListener) //Добавить листенер на примере ресайклВью
}