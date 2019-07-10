package com.example.myfirstandroid.cw9

import android.view.View
import com.example.myfirstandroid.cw9.entity.CoordinateParams

interface CarRepository {

    fun getCarByCoordinate(params: CoordinateParams, listener: View.OnClickListener) //Добавить листенер на примере ресайклВью
}