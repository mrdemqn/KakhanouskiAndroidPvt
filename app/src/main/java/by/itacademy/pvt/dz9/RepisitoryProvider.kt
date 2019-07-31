package by.itacademy.pvt.dz9

import by.itacademy.pvt.dz12.StudentRepository
import by.itacademy.pvt.dz12.StudentRepositoryRemote

private const val BASE_URL = "https://api.backendless.com/AE8B0E02-C2C7-6438-FF2B-4BF980226800/199E1F13-D27C-1269-FFD2-07A48A4F7700/"

fun provideCarRepository(): CarRepository {

    return CarRepositoryRemote(
        NetProvider.provideApi(
            NetProvider.provideRetrofit(
                "http://kiparo.ru/",
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}

fun provideStudentRepository(): StudentRepository {

    return StudentRepositoryRemote(
        NetProvider.provideStudentApi(
            NetProvider.provideRetrofit(
                BASE_URL,
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}