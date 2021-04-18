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
    private lateinit var navigationView: NavigationView

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
                val intent = Intent(Intent(activity, ActivityNewGames::class.java))
                activity.startActivity(intent)
                return true
            }
            R.id.listPlatforms -> {
                val intent = Intent(Intent(activity, ActivityNewGames::class.java))
                activity.startActivity(intent)
                return true
            }
            R.id.gameGenres -> {
                val intent = Intent(Intent(activity, ActivityNewGames::class.java))
                activity.startActivity(intent)
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
        this.navigationView = activity.findViewById(R.id.drawerMenu)
        this.navigationView.itemIconTintList = null
    }
}