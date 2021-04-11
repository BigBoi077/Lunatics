package cegepst.example.lunatics.viewModels

import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.Game
import cegepst.example.lunatics.services.RawgService

class SingleGameViewModel : ViewModel() {

    lateinit var game: Game

    private val rawgService by lazy {
        RawgService.create()
    }
}