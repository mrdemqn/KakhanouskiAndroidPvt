package com.example.myfirstandroid.cw9

import android.view.View
import com.example.myfirstandroid.cw9.entity.CoordinateParams

// CRUD - create, read, update, delete
class CarRepositoryRemote(private val api: Api) : CarRepository {

    override fun getCarByCoordinate(params: CoordinateParams, listener: View.OnClickListener) {
        //TODO СДЕЛАТЬ РЕАЛИЗАЦИЮ ИМПЛИМЕНТАЦИИ
        //api.getCarsByCoordinate()
    }
}