package cegepst.example.lunatics.services

import cegepst.example.lunatics.models.GameResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.rawg.io/api/"

interface RawgService {

    @GET("/")
    fun getGames(
            @Query("apikey") apikey: String,
            @Query("games") searchValue: String,
    ): Call<GameResult>

    companion object {
        fun create(): RawgService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    ).baseUrl(BASE_URL)
                    .build()

            return retrofit.create(RawgService::class.java)
        }
    }
}