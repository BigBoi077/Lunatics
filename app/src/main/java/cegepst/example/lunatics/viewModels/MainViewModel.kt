package cegepst.example.lunatics.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.Game

class MainViewModel : ViewModel() {

    val adapter =
    val games = MutableLiveData(listOf<Game>())

    fun getGames(): LiveData<List<Game>> {
        return games
    }
}