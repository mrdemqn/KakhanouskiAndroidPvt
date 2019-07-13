package by.itacademy.pvt.dz9

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

object NetProvider {

    private var api: Api? = null

    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    fun provideOkHttp(): OkHttpClient {


        val okhttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okhttpBuilder.addInterceptor(logging)
        }

        //okhttpBuilder.connectTimeout(5, TimeUnit.SECONDS) //Ожидание ответа от сервера. Если время кончилось, то ошибка

        val okhttpClient = okhttpBuilder.build()

        return okhttpClient
    }

    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }

    fun provideApi(retrofit: Retrofit): Api {
        if (api == null) {
        api = retrofit.create<Api>(Api::class.java)
        }
        return api!!
    }
}