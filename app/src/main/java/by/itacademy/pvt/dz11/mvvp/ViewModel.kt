package by.itacademy.pvt.dz11.mvvp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.pvt.dz9.CarRepositoryResult
import by.itacademy.pvt.dz9.CarRepository
import by.itacademy.pvt.dz9.provideCarRepository
import by.itacademy.pvt.dz9.entity.Coordinate
import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.Poi

class ViewModel : ViewModel(), CarRepositoryResult {

    private val carRepository: CarRepository = provideCarRepository()
    private lateinit var data: List<Poi>

    val state: MutableLiveData<CarRepositoryState> by lazy(LazyThreadSafetyMode.NONE)
    { MutableLiveData<CarRepositoryState>() }

    val extremeSelectedPoi: MutableLiveData<Poi> by lazy(LazyThreadSafetyMode.NONE)
    { MutableLiveData<Poi>() }

    override fun onSuccess(data: List<Poi>) {
        state.value = CarRepositoryState.SuccessProgress(data)
        this.data = data
    }

    override fun onError(throwable: Throwable) {
        state.value = CarRepositoryState.ProgressFailed(throwable)
    }

    init {
        carRepository.getCarByCoordinate(
            CoordinateParams(
                Coordinate(2500.0,250.0),
                Coordinate(3400.0, 340.0)),
            this)
    }

    fun load() {
        if (state.value is CarRepositoryState.SuccessProgress) {
            return
        }
    }

    fun clickByCar(item: Poi) {
        extremeSelectedPoi.value = item
    }

}