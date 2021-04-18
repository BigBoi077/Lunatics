package cegepst.example.lunatics.services

import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.models.results.AchievementsResults
import cegepst.example.lunatics.models.results.GameResult
import cegepst.example.lunatics.models.results.GenreResult
import cegepst.example.lunatics.models.results.PlatformResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.rawg.io/api/"
private const val GAME_ENDPOINT = "games"
private const val ACHIEVEMENT_ENDPOINT = "achievements"
private const val SAME_GAMES_ENDPOINT = "game-series"
private const val PLATFORM_ENDPOINT = "platforms"
private const val GENRE_ENDPOINT = "genres"

interface RawgService {

    @GET(GAME_ENDPOINT)
    fun getGames(
        @Query("key") key: String,
        @Query("page_size") size: Int,
        @Query("page") page: Int,
        @Query("dates") date: String
    ): Call<GameResult>

    @GET(GAME_ENDPOINT)
    fun getNewGames(
        @Query("key") apiKey: String,
        @Query("page_size") wantedSize: Int,
        @Query("page") page: Int,
        @Query("dates") montAgo: String,
        @Query("ordering") ordering: String
    ): Call<GameResult>

    @GET("$GAME_ENDPOINT/{id}")
    fun getSingleGame(
        @Path("id") gameId: String,
        @Query("key") apikey: String
    ): Call<Game>

    @GET("$GAME_ENDPOINT/{id}/$ACHIEVEMENT_ENDPOINT")
    fun getAchievements(
        @Path("id") gameId: String,
        @Query("key") apikey: String
    ): Call<AchievementsResults>

    @GET("$GAME_ENDPOINT/{id}/$SAME_GAMES_ENDPOINT")
    fun getAllSameGames(
        @Path("id") gameId: String,
        @Query("key") apikey: String
    ): Call<GameResult>

    @GET(GAME_ENDPOINT)
    fun getGamesByPlatform(
        @Query("key") apikey: String,
        @Query("platforms") platformId: String,
        @Query("page_size") wantedSize: Int,
        @Query("page") page: Int
    ): Call<GameResult>

    @GET(PLATFORM_ENDPOINT)
    fun getPlatforms(
        @Query("key") apiKey: String,
        @Query("page_size") wantedSize: Int,
        @Query("page") page: Int
    ): Call<PlatformResult>

    @GET(GENRE_ENDPOINT)
    fun getGenres(
        @Query("key") apiKey: String,
        @Query("page_size") wantedSize: Int,
        @Query("page") page: Int
    ): Call<GenreResult>

    @GET(GAME_ENDPOINT)
    fun getGamesByGenre(
        @Query("key") apikey: String,
        @Query("genres") genreId: String,
        @Query("page_size") wantedSize: Int,
        @Query("page") page: Int
    ): Call<GameResult>

    companion object {
        const val API_KEY = "762f85b6be7c4c90ba98b1c82b67a075"

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