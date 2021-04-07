package cegepst.example.lunatics

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

private const val API_KEY = "762f85b6be7c4c90ba98b1c82b67a075"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDrawerMenu()
    }

    private fun initDrawerMenu() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer)
        actionBarDrawerToggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.actionOpen,
                R.string.actionClose
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        val navigationView = findViewById<NavigationView>(R.id.drawerMenu)
        navigationView.itemIconTintList = null
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
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
}