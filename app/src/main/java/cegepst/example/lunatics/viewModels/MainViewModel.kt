package cegepst.example.lunatics.viewModels

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.models.managers.ErrorManager
import cegepst.example.lunatics.models.managers.LoadingManager
import cegepst.example.lunatics.models.results.GameResult
import cegepst.example.lunatics.services.RawgService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val YEAR_AGO = 1L
private const val WANTED_SIZE = 10
private const val DATE_PATTERN = "yyyy-MM-dd"

class MainViewModel : ViewModel() {

    private val loadingManager = LoadingManager()
    private val errorManager = ErrorManager()
    val games = MutableLiveData(listOf<Game>())
    var page: Int = 1

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
        val yearAgo = LocalDate.now().minusYears(YEAR_AGO).format(DateTimeFormatter.ofPattern(DATE_PATTERN))
        val date = "$yearAgo,${LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN))}"
        rawgService.getGames(RawgService.API_KEY, WANTED_SIZE, page, date)
                .enqueue(object : Callback<GameResult> {
                    override fun onResponse(call: Call<GameResult>, response: Response<GameResult>) {
                        if (games.value!!.isEmpty()) {
                            games.value = response.body()!!.games
                        } else {
                            makeTempList(games, response)
                        }
                        loadingManager.isSuccess()
                        page++
                    }

                    override fun onFailure(call: Call<GameResult>, t: Throwable) {
                        errorManager.raiseError(t.message ?: "Unrecognized error")
                        loadingManager.isError()
                    }
                })
    }

    private fun makeTempList(data: MutableLiveData<List<Game>>, response: Response<GameResult>) {
        val list = data.value as ArrayList<Game>
        list.addAll(response.body()!!.games)
        data.value = list
    }
}