package cegepst.example.lunatics.views.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.models.interfaces.BaseActivity
import cegepst.example.lunatics.models.managers.DrawerMenuManager
import cegepst.example.lunatics.viewModels.NewGamesViewModel
import cegepst.example.lunatics.views.adapters.GameAdapter
import cegepst.example.lunatics.views.fragments.GameFragment
import com.google.android.material.navigation.NavigationView

private const val TITLE = "New games"

class ActivityNewGames : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    BaseActivity {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var menu: NavigationView
    private lateinit var viewModel: NewGamesViewModel
    private lateinit var adapter: GameAdapter
    private var games = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun setDrawerMenu(element: ActionBarDrawerToggle) {
        actionBarDrawerToggle = element
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initDrawerMenu() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.actionOpen, R.string.actionClose)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        menu = findViewById(R.id.drawerMenu)
        menu.setNavigationItemSelectedListener {
            DrawerMenuManager.handleChosenAction(it, this)
            true
        }
        supportActionBar?.title = TITLE
    }

    override fun initVariables() {
        viewModel = ViewModelProvider(this).get(NewGamesViewModel::class.java)
        adapter = GameAdapter(games)
    }

    override fun initFragment() {
        val lambda = { actionLoad() }
        supportFragmentManager.beginTransaction()
            .add(
                R.id.gameContainer,
                GameFragment.newInstance(adapter, lambda)
            )
            .commit()
    }

    override fun loadContent() {
        viewModel.fetchNewGames()
        viewModel.getGames().observe(this, {
            games.clear()
            games.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun actionLoad() {
        if (canLoadMoreGames()) {
            viewModel.fetchNewGames()
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