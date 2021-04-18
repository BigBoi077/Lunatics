package cegepst.example.lunatics.viewModels

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.baseModels.Platform
import cegepst.example.lunatics.models.managers.ErrorManager
import cegepst.example.lunatics.models.managers.LoadingManager
import cegepst.example.lunatics.models.results.PlatformResult
import cegepst.example.lunatics.services.RawgService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlatformViewModel : ViewModel() {

    private val loadingManager = LoadingManager()
    private val errorManager = ErrorManager()
    val platforms = MutableLiveData(listOf<Platform>())
    var next: String? = null
    var page: Int = 1

    private val rawgService by lazy {
        RawgService.create()
    }

    fun getPlatforms(): LiveData<List<Platform>> {
        return platforms
    }

    fun giveComponents(textView: TextView) {
        errorManager.setComponents(textView)
    }

    fun fetchPlatforms() {
        loadingManager.isLoading()
        rawgService.getPlatforms(
            RawgService.API_KEY,
            WANTED_SIZE,
            page,
        )
            .enqueue(object : Callback<PlatformResult> {
                override fun onResponse(
                    call: Call<PlatformResult>,
                    response: Response<PlatformResult>
                ) {
                    next = response.body()?.next
                    if (platforms.value!!.isEmpty()) {
                        platforms.value = response.body()!!.platforms
                    } else {
                        makeTempList(platforms, response)
                    }
                    loadingManager.isSuccess()
                    page++
                }

                override fun onFailure(call: Call<PlatformResult>, t: Throwable) {
                    errorManager.raiseError(t.message ?: "Unrecognized error")
                    loadingManager.isError()
                }
            })
    }

    private fun makeTempList(
        data: MutableLiveData<List<Platform>>,
        response: Response<PlatformResult>
    ) {
        val list = data.value as ArrayList<Platform>
        if (response.body()?.platforms != null) {
            list.addAll(response.body()!!.platforms)
        }
        data.value = list
    }
}
