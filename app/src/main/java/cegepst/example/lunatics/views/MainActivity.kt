package cegepst.example.lunatics.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.DrawerMenuManager
import cegepst.example.lunatics.viewModels.MainViewModel
import com.google.android.material.navigation.NavigationView

private const val API_KEY = "762f85b6be7c4c90ba98b1c82b67a075"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var drawerMenuManager: DrawerMenuManager
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDrawerMenu()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        loadContent()
    }

    private fun loadContent() {
        viewModel.getGames().observe(this, {
            // TODO : caster le games en ArrayList
            viewModel.games as ArrayList
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return drawerMenuManager.handleChosenAction(item)
    }
}
