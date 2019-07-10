package by.itacademy.pvt.dz9

fun provideCarRepository(): CarRepository {

    return CarRepositoryRemote(
        NetProvider.provideApi(
            NetProvider.provideRetrofit(
                "http",
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}