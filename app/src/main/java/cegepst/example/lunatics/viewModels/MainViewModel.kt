package cegepst.example.lunatics.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.Game

class MainViewModel : ViewModel() {

    private val games = MutableLiveData(listOf<Game>())

}