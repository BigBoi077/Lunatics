package cegepst.example.lunatics.viewModels

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.baseModels.Genre
import cegepst.example.lunatics.models.managers.ErrorManager
import cegepst.example.lunatics.models.managers.LoadingManager
import cegepst.example.lunatics.models.results.GenreResult
import cegepst.example.lunatics.services.RawgService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreViewModel : ViewModel() {

    private val loadingManager = LoadingManager()
    private val errorManager = ErrorManager()
    val genres = MutableLiveData(listOf<Genre>())
    var next: String? = null
    var page: Int = 1

    private val rawgService by lazy {
        RawgService.create()
    }

    fun getGenres(): LiveData<List<Genre>> {
        return genres
    }

    fun giveComponents(textView: TextView) {
        errorManager.setComponents(textView)
    }

    fun fetchGenres() {
        loadingManager.isLoading()
        rawgService.getGenres(
            RawgService.API_KEY,
            WANTED_SIZE,
            page
        )
            .enqueue(object : Callback<GenreResult> {
                override fun onResponse(call: Call<GenreResult>, response: Response<GenreResult>) {
                    next = response.body()?.next
                    if (genres.value!!.isEmpty()) {
                        genres.value = response.body()!!.genres
                    } else {
                        makeTempList(genres, response)
                    }
                    loadingManager.isSuccess()
                    page++
                }

                override fun onFailure(call: Call<GenreResult>, t: Throwable) {
                    errorManager.raiseError(t.message ?: "Unrecognized error")
                    loadingManager.isError()
                }
            })
    }

    private fun makeTempList(data: MutableLiveData<List<Genre>>, response: Response<GenreResult>) {
        val list = data.value as ArrayList<Genre>
        if (response.body()?.genres != null) {
            list.addAll(response.body()!!.genres)
        }
        data.value = list
    }
}
