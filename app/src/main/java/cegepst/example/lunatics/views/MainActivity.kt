package cegepst.example.lunatics.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.DrawerMenuManager
import cegepst.example.lunatics.models.Game
import cegepst.example.lunatics.viewModels.MainViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var drawerMenuManager: DrawerMenuManager
    private lateinit var viewModel: MainViewModel
    private lateinit var games: ArrayList<Game>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDrawerMenu()
        initFragment()
        initVariables()
        loadContent()
    }

    private fun initVariables() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.giveComponents(findViewById(R.id.errorBubble), findViewById(R.id.listGames))
        games = ArrayList()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction().add(R.id.gameContainer, GameFragment.newInstance("Welcome"))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return drawerMenuManager.handleChosenAction(item)
    }

    private fun loadContent() {
        viewModel.fetchGames()
        viewModel.getGames().observe(this, {
            games.clear()
            games.addAll(it)
            viewModel.adapter.notifyDataSetChanged()
        })
    }

    private fun initDrawerMenu() {
        drawerMenuManager = DrawerMenuManager(this)
        drawerMenuManager.initDrawerMenu { drawer: ActionBarDrawerToggle -> setDrawerMenu(drawer) }
        supportActionBar?.title = ""
    }

    private fun setDrawerMenu(element: ActionBarDrawerToggle) {
        actionBarDrawerToggle = element
    }
}
