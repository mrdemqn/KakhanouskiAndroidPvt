package by.itacademy.pvt.dz9

import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.CarResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// CRUD - create, read, update, delete
class CarRepositoryRemote(private val api: Api) : CarRepository {

    override fun getCarByCoordinate(params: CoordinateParams, listener: CarRepositoryResult) {
        api.getCarsByCoordinate(
            params.coordinate1.latitude,
            params.coordinate1.longitude,
            params.coordinate2.latitude,
            params.coordinate2.longitude
        ).enqueue(object : Callback<CarResponse> {
            override fun onResponse(call: Call<CarResponse>, response: Response<CarResponse>) {
                if (response.body()?.poiList != null) {
                    listener.onSuccess(response.body()!!.poiList)
                } else {
                    listener.onError(Throwable("Error. Body is null"))
                }
            }

            override fun onFailure(call: Call<CarResponse>, throwable: Throwable) {
                listener.onError(throwable)
            }
        })
    }
}