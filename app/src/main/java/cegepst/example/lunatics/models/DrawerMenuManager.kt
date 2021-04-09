package cegepst.example.lunatics.models

import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import cegepst.example.lunatics.R
import cegepst.example.lunatics.views.MainActivity
import com.google.android.material.navigation.NavigationView

class DrawerMenuManager(mainActivity: MainActivity) {

    private var mainActivity = mainActivity
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    fun handleChosenAction(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.popularGame -> {
                return true
            }
            R.id.newGames -> {
                return true
            }
            R.id.gamesToCome -> {
                return true
            }
            R.id.gamePlateforms -> {
                return true
            }
            R.id.gameGenres -> {
                return true
            }
            else -> return false
        }
    }

    fun initDrawerMenu(lambda: (ActionBarDrawerToggle) -> Unit) {
        val drawerLayout = mainActivity.findViewById<DrawerLayout>(R.id.drawer)
        actionBarDrawerToggle = ActionBarDrawerToggle(
                mainActivity,
                drawerLayout,
                R.string.actionOpen,
                R.string.actionClose
        )
        lambda(actionBarDrawerToggle)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        if (mainActivity.supportActionBar != null) {
            mainActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        val navigationView = mainActivity.findViewById<NavigationView>(R.id.drawerMenu)
        navigationView.setNavigationItemSelectedListener(mainActivity)
        navigationView.itemIconTintList = null
    }
}