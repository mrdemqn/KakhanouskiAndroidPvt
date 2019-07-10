package com.example.myfirstandroid.cw9

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