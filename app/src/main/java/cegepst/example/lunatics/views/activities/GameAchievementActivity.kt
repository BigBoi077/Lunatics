package cegepst.example.lunatics.views.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Achievement
import cegepst.example.lunatics.models.interfaces.BaseActivity
import cegepst.example.lunatics.models.managers.DrawerMenuManager
import cegepst.example.lunatics.viewModels.GameAchievementsViewModel
import cegepst.example.lunatics.views.adapters.AchievementAdapter
import cegepst.example.lunatics.views.fragments.GameAchievementsFragment
import com.google.android.material.navigation.NavigationView

private const val TITLE = "Game achievements"

class GameAchievementActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener, BaseActivity {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var menu: NavigationView
    private lateinit var viewModel: GameAchievementsViewModel
    private lateinit var adapter: AchievementAdapter
    private var achievements = ArrayList<Achievement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDrawerMenu()
        initVariables()
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
        viewModel = ViewModelProvider(this).get(GameAchievementsViewModel::class.java)
        viewModel.giveComponents(findViewById(R.id.errorBubble))
        adapter = AchievementAdapter(achievements)
    }

    override fun initFragment() {
        return
    }

    override fun loadContent() {
        val lambda = { achievements: ArrayList<Achievement> -> launchFragment(achievements) }
        viewModel.fetchGameAchievements(intent.getIntExtra("gameId", 1), lambda)
    }

    private fun launchFragment(achievements: ArrayList<Achievement>) {
        supportActionBar?.title = "Game achievements"
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragmentContainer,
                GameAchievementsFragment.newInstance(intent.getIntExtra("gameId", 1), achievements)
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