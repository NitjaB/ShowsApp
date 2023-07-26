package infinuma.android.shows.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import infinuma.android.shows.utils.TokenRepositoryInstance
import java.util.concurrent.TimeUnit
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RemoteApiSingleton {

    private const val BASE_URL = """https://tv-shows.infinum.academy/#section/Tv-Show-API/"""
    private const val USER_TOKEN_HEADER = "access-token"

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
            .addNetworkInterceptor(createTokenInterceptor())
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

    private fun createTokenInterceptor(): (chain: Interceptor.Chain) -> Response = { chain ->
        val originalRequest = chain.request()
        val token = TokenRepositoryInstance.get().getToken()
        val newRequest = if (!token.isNullOrBlank()) {
            originalRequest.newBuilder()
                .header(USER_TOKEN_HEADER, token)
                .method(originalRequest.method, originalRequest.body)
                .build()
        } else {
            originalRequest
        }

        val response = chain.proceed(newRequest)
        TokenRepositoryInstance.get().setUserToken(
            response.headers[USER_TOKEN_HEADER] ?: ""
        )
        response
    }
}