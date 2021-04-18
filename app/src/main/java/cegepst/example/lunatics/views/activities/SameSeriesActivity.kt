package cegepst.example.lunatics.views.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.models.interfaces.BaseActivity
import cegepst.example.lunatics.models.managers.DrawerMenuManager
import cegepst.example.lunatics.viewModels.SameSeriesViewModel
import cegepst.example.lunatics.views.adapters.GameAdapter
import cegepst.example.lunatics.views.fragments.SameSeriesFragment
import com.google.android.material.navigation.NavigationView

private const val TITLE = ""

class SameSeriesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    BaseActivity {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var menu: NavigationView
    private lateinit var viewModel: SameSeriesViewModel
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
        viewModel = ViewModelProvider(this).get(SameSeriesViewModel::class.java)
        viewModel.giveComponents(findViewById(R.id.errorBubble))
        adapter = GameAdapter(games)
    }

    override fun initFragment() {
        return
    }

    override fun loadContent() {
        val lambda = { games: ArrayList<Game> -> loadFragment(games) }
        viewModel.fetchSameSeriesGame(intent.getIntExtra("gameId", 0), lambda)
    }

    private fun loadFragment(games: ArrayList<Game>) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragmentContainer,
                SameSeriesFragment.newInstance(games)
            )
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}