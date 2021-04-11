package cegepst.example.lunatics.viewModels

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.services.RawgService
import com.google.android.material.navigation.NavigationView

class SingleGameViewModel : ViewModel(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var game: Game

    private val rawgService by lazy {
        RawgService.create()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}