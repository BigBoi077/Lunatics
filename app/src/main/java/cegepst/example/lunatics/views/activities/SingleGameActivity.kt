package cegepst.example.lunatics.views.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Platform
import cegepst.example.lunatics.models.managers.DrawerMenuManager
import cegepst.example.lunatics.viewModels.MainViewModel
import cegepst.example.lunatics.views.adapters.PlatformAdapter
import com.google.android.material.navigation.NavigationView

class SingleGameActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var drawerMenuManager: DrawerMenuManager
    private lateinit var platformAdapter: PlatformAdapter
    private lateinit var viewModel: MainViewModel
    private var platforms = ArrayList<Platform>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_game)
    }

    private fun initDrawerMenu() {
        drawerMenuManager = DrawerMenuManager(this, supportActionBar)
        drawerMenuManager.initDrawerMenu { drawer: ActionBarDrawerToggle -> setDrawerMenu(drawer) }
        supportActionBar?.title = ""
    }

    private fun setDrawerMenu(element: ActionBarDrawerToggle) {
        actionBarDrawerToggle = element
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}