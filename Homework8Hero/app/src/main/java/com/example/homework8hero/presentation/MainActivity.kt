package com.example.homework8hero.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.homework8hero.R
import com.example.homework8hero.core.App
import com.example.homework8hero.databinding.ActivityMainBinding
import com.example.homework8hero.presentation.util.NetworkManager
import com.example.homework8hero.presentation.util.Screens
import com.example.homework8hero.presentation.util.Screens.Heroes
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component by lazy { (application as App).component }
    private val navigator: Navigator = object : AppNavigator(this, R.id.main_fragment_container) {

        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    @Inject
    lateinit var networkManager: NetworkManager

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        component.inject(this)
        networkManager.registerCallback()
        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Heroes())))
        }

        networkManager.isConnectedNetwork.observe(this) { network ->
            binding.notification.root.isVisible = !network
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                router.navigateTo(Screens.Info())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        networkManager.unregisterCallback()
    }
}