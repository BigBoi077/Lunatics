package cegepst.example.lunatics.models.managers

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import cegepst.example.lunatics.R
import cegepst.example.lunatics.views.activities.GamesToComeActivity
import cegepst.example.lunatics.views.activities.MainActivity
import cegepst.example.lunatics.views.activities.NewGamesActivity
import cegepst.example.lunatics.views.activities.PlatformsActivity

class DrawerMenuManager {

    companion object {
        fun handleChosenAction(menuItem: MenuItem, activity: Activity) {
            when (menuItem.itemId) {
                R.id.popularGame -> {
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                }
                R.id.newGames -> {
                    val intent = Intent(activity, NewGamesActivity::class.java)
                    activity.startActivity(intent)
                }
                R.id.gamesToCome -> {
                    val intent = Intent(activity, GamesToComeActivity::class.java)
                    activity.startActivity(intent)
                }
                R.id.listPlatforms -> {
                    val intent = Intent(activity, PlatformsActivity::class.java)
                    activity.startActivity(intent)
                }
                R.id.gameGenres -> {
                    val intent = Intent(activity, NewGamesActivity::class.java)
                    activity.startActivity(intent)
                }
            }
        }
    }
}