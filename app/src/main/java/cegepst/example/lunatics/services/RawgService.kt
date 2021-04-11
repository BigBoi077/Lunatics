package cegepst.example.lunatics.services

import cegepst.example.lunatics.models.restults.GameResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.rawg.io/api/"
private const val GAME_ENDPOINT = "games"

interface RawgService {

    val next: String
        get() = ""

    @GET(GAME_ENDPOINT)
    fun getGames(
            @Query("key") apikey: String,
            @Query("page_size") size: Int
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