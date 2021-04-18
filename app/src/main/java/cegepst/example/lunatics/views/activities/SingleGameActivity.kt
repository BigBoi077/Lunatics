    package cegepst.example.lunatics.views.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.models.baseModels.PlatformParent
import cegepst.example.lunatics.models.interfaces.BaseActivity
import cegepst.example.lunatics.models.managers.DrawerMenuManager
import cegepst.example.lunatics.viewModels.SameSeriesViewModel
import cegepst.example.lunatics.viewModels.SingleGameViewModel
import cegepst.example.lunatics.views.adapters.SimplePlatformAdapter
import cegepst.example.lunatics.views.fragments.SingleGameFragment
import com.google.android.material.navigation.NavigationView

class SingleGameActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    BaseActivity {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var menu: NavigationView
    private lateinit var adapterSimple: SimplePlatformAdapter
    private lateinit var viewModel: SingleGameViewModel
    private var platforms = ArrayList<PlatformParent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDrawerMenu()
        initVariables()
        loadContent()
        initFragment()
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
        supportActionBar?.title = "Popular games"
    }

    override fun initVariables() {
        viewModel = ViewModelProvider(this).get(SingleGameViewModel::class.java)
        viewModel.giveComponents(findViewById(R.id.errorBubble))
        adapterSimple = SimplePlatformAdapter(platforms)
    }

    override fun initFragment() {
        return
    }

    override fun loadContent() {
        val tempViewModel = ViewModelProvider(this).get(SameSeriesViewModel::class.java)
        val lambda = { game: Game, length: Int -> launchFragment(game, length) }
        viewModel.fetchSingleGames(intent.getIntExtra("gameId", 1), lambda, tempViewModel)
    }

    private fun launchFragment(game: Game, lenght: Int) {
        supportActionBar?.title = game.name
        supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragmentContainer,
                    SingleGameFragment.newInstance(intent.getIntExtra("gameId", 1), game, lenght)
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