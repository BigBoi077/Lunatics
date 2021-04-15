package cegepst.example.lunatics.models.managers

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import cegepst.example.lunatics.R
import cegepst.example.lunatics.views.activities.ActivityNewGames
import cegepst.example.lunatics.views.activities.MainActivity
import com.google.android.material.navigation.NavigationView

class DrawerMenuManager(private var activity: Activity, private var supportActionBar: androidx.appcompat.app.ActionBar?) {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    fun handleChosenAction(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.popularGame -> {
                val intent = Intent(Intent(activity, MainActivity::class.java))
                activity.startActivity(intent)
                return true
            }
            R.id.newGames -> {
                val intent = Intent(Intent(activity, ActivityNewGames::class.java))
                activity.startActivity(intent)
                return true
            }
            R.id.gamesToCome -> {
                return true
            }
            R.id.listPlatforms -> {
                return true
            }
            R.id.gameGenres -> {
                return true
            }
            else -> return false
        }
    }

    fun initDrawerMenu(lambda: (ActionBarDrawerToggle) -> Unit) {
        val drawerLayout = activity.findViewById<DrawerLayout>(R.id.drawer)
        actionBarDrawerToggle = ActionBarDrawerToggle(
                activity,
                drawerLayout,
                R.string.actionOpen,
                R.string.actionClose
        )
        lambda(actionBarDrawerToggle)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        val navigationView = activity.findViewById<NavigationView>(R.id.drawerMenu)
        navigationView.itemIconTintList = null
    }
}