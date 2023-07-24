package infinuma.android.shows.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.EmptySerializersModule
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RemoteApiSingleton {

    private const val BASE_URL = """https://tv-shows.infinum.academy/#section/Tv-Show-API/"""

    private lateinit var okHttp: OkHttpClient
    private lateinit var retrofit: Retrofit
    private lateinit var showRemoteApi: ShowRemoteApi

    fun bind(appContext: Context) {
        okHttp = createOkHttpClint(appContext)
        retrofit = createRetrofit()
        showRemoteApi = createShowRemoteApi()
    }

    fun getRemoteApi() = showRemoteApi

    private fun createOkHttpClint(appContext: Context) =
        OkHttpClient.Builder()
            .cache(Cache(appContext.cacheDir, 1024 * 1024 * 10L))
            .connectTimeout(5, TimeUnit.MINUTES)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(ChuckerInterceptor.Builder(appContext).build())
            .build()

    private fun createRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json(
            builderAction = {
                this.ignoreUnknownKeys = true
            }
        )
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttp)
            .build()
    }

    private fun createShowRemoteApi() = retrofit.create(ShowRemoteApi::class.java)


}