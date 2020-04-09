package com.orost.covid19

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.orost.covid19.ui.HomeFragment
import com.orost.covid19.ui.WorldFragment
import com.orost.covid19.utils.addToBackStack
import com.orost.covid19.utils.replaceWithBackStack
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.addToBackStack(R.id.fragment_container, get<HomeFragment>())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_plots -> {
            supportFragmentManager.replaceWithBackStack(
                R.id.fragment_container,
                get<WorldFragment>()
            )
            true
        }

        R.id.action_world -> {
            supportFragmentManager.replaceWithBackStack(
                R.id.fragment_container,
                get<WorldFragment>()
            )
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
