package cegepst.example.lunatics.views.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Platform
import cegepst.example.lunatics.models.interfaces.BaseActivity
import cegepst.example.lunatics.models.managers.DrawerMenuManager
import cegepst.example.lunatics.viewModels.PlatformViewModel
import cegepst.example.lunatics.views.adapters.PlatformAdapter
import cegepst.example.lunatics.views.fragments.PlatformFragment
import com.google.android.material.navigation.NavigationView

private const val TITLE = "Platforms"
private const val MAX_PLATFORMS = 50

class PlatformsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    BaseActivity {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var menu: NavigationView
    private lateinit var viewModel: PlatformViewModel
    private lateinit var adapter: PlatformAdapter
    private var platforms = ArrayList<Platform>()

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
        viewModel = ViewModelProvider(this).get(PlatformViewModel::class.java)
        viewModel.giveComponents(findViewById(R.id.errorBubble))
        adapter = PlatformAdapter(platforms)
    }

    override fun initFragment() {
        val lambda = { actionLoad() }
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragmentContainer,
                PlatformFragment.newInstance(adapter, lambda)
            )
            .commit()
    }

    override fun loadContent() {
        viewModel.fetchPlatforms()
        viewModel.getPlatforms().observe(this, {
            platforms.clear()
            platforms.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun actionLoad() {
        viewModel.fetchPlatforms()
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