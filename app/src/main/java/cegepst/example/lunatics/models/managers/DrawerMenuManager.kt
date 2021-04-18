package cegepst.example.lunatics.models.managers

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import cegepst.example.lunatics.R
import cegepst.example.lunatics.views.activities.ActivityNewGames
import cegepst.example.lunatics.views.activities.MainActivity

class DrawerMenuManager {

    companion object {
        fun handleChosenAction(menuItem: MenuItem, activity: Activity) {
            when (menuItem.itemId) {
                R.id.popularGame -> {
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                }
                R.id.newGames -> {
                    val intent = Intent(activity, ActivityNewGames::class.java)
                    activity.startActivity(intent)
                }
                R.id.gamesToCome -> {
                    val intent = Intent(activity, ActivityNewGames::class.java)
                    activity.startActivity(intent)
                }
                R.id.listPlatforms -> {
                    val intent = Intent(activity, ActivityNewGames::class.java)
                    activity.startActivity(intent)
                }
                R.id.gameGenres -> {
                    val intent = Intent(activity, ActivityNewGames::class.java)
                    activity.startActivity(intent)
                }
            }
        }
    }
}