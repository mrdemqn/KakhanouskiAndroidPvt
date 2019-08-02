package by.itacademy.pvt.dz11.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.pvt.dz11.mvvm.CarRepositoryState.*
import by.itacademy.pvt.dz9.CarRepository
import by.itacademy.pvt.dz9.provideCarRepository
import by.itacademy.pvt.dz9.entity.Coordinate
import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.Poi
import io.reactivex.android.schedulers.AndroidSchedulers.*
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.*

class ViewModel : ViewModel() {

    private val carRepository: CarRepository = provideCarRepository()
    private var data: List<Poi> = mutableListOf()
    var disposable: Disposable? = null

    val state: MutableLiveData<CarRepositoryState> by lazy(LazyThreadSafetyMode.NONE)
    { MutableLiveData<CarRepositoryState>() }

    val extremeSelectedPoi: MutableLiveData<Poi> by lazy(LazyThreadSafetyMode.NONE)
    { MutableLiveData<Poi>() }

    init {
        disposable = carRepository
            .getCar(
                CoordinateParams(
                Coordinate(2342.0, 342.0),
                Coordinate(3242.0, 3453.0)))
            .subscribeOn(io())
            .observeOn(mainThread())
            .subscribe({itCarResponse ->
                data = itCarResponse.poiList
                state.value = SuccessProgress(carList = data)
                Log.e("AAA", "load success")
            }, { throwable ->
                state.value = ProgressFailed(throwable = throwable)
            })
    }

    fun clickByCar(item: Poi) {
        extremeSelectedPoi.value = item
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}