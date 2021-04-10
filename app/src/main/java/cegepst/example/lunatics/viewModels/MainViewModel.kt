package cegepst.example.lunatics.viewModels

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.ErrorManager
import cegepst.example.lunatics.models.Game
import cegepst.example.lunatics.models.GameResult
import cegepst.example.lunatics.models.LoadingManager
import cegepst.example.lunatics.services.RawgService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val API_KEY = "762f85b6be7c4c90ba98b1c82b67a075"

class MainViewModel : ViewModel() {

    val games = MutableLiveData(listOf<Game>())
    private val loadingManager = LoadingManager()
    private val errorManager = ErrorManager()

    private val rawgService by lazy {
        RawgService.create()
    }

    fun getGames(): LiveData<List<Game>> {
        return games
    }

    fun giveComponents(textView: TextView) {
        errorManager.setComponents(textView)
    }

    fun fetchGames() {
        loadingManager.isLoading()
        rawgService.getGames(API_KEY, 10).enqueue(object : Callback<GameResult> {
            override fun onResponse(call: Call<GameResult>, response: Response<GameResult>) {
                games.value = response.body()!!.games

                Log.d("RESPONSE", Gson().toJson(response.body()))

                loadingManager.isSuccess()
            }

            override fun onFailure(call: Call<GameResult>, t: Throwable) {
                errorManager.raiseError(t.message ?: "Unrecognized error")
                loadingManager.isError()
            }
        })
    }
}