package infinuma.android.shows.network

import infinuma.android.shows.network.models.ListShowsResponse
import infinuma.android.shows.network.models.RegisterUserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ShowRemoteApi {

    @FormUrlEncoded
    @POST("users")
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String,
    ): RegisterUserResponse

    @FormUrlEncoded
    @POST("users/sign_in")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterUserResponse

    @GET("shows")
    suspend fun listShows(
        @Query("page") page: Int = 1,
        @Query("Items") items: Int = 60,
    ): ListShowsResponse
}
