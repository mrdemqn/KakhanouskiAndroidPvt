package by.itacademy.pvt.dz11.mvvm

import by.itacademy.pvt.dz9.entity.Poi

sealed class CarRepositoryState {
    class ProgressFailed(val throwable: Throwable) : CarRepositoryState()
    class SuccessProgress(val carList: List<Poi>) : CarRepositoryState()
    class Error(val throwable: Throwable) : CarRepositoryState()
}