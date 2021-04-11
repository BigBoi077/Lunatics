package cegepst.example.lunatics.views.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.models.managers.DrawerMenuManager
import cegepst.example.lunatics.viewModels.MainViewModel
import cegepst.example.lunatics.views.adapters.GameAdapter
import cegepst.example.lunatics.views.fragments.GameFragment
import com.google.android.material.navigation.NavigationView

private const val MAX_GAMES = 50

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var drawerMenuManager: DrawerMenuManager
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: GameAdapter
    private var games = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDrawerMenu()
        initVariables()
        initFragment()
        loadContent()
    }

    private fun initVariables() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.giveComponents(findViewById(R.id.errorBubble))
        adapter = GameAdapter(games)
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.gameContainer, GameFragment.newInstance("Welcome", adapter, findViewById(R.id.header)))
                .commit()
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
            adapter.notifyDataSetChanged()
        })
    }

    private fun initDrawerMenu() {
        drawerMenuManager = DrawerMenuManager(this, supportActionBar)
        drawerMenuManager.initDrawerMenu { drawer: ActionBarDrawerToggle -> setDrawerMenu(drawer) }
        supportActionBar?.title = ""
    }

    private fun setDrawerMenu(element: ActionBarDrawerToggle) {
        actionBarDrawerToggle = element
    }

    fun actionLoad(view: View) {
        if (canLoadMoreGames()) {
            viewModel.fetchGames()
        } else {
            alert(resources.getString(R.string.noMoreGames))
        }
    }

    private fun alert(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun canLoadMoreGames(): Boolean {
        return games.size < MAX_GAMES
    }
}
