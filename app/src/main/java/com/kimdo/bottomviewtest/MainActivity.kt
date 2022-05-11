package com.kimdo.bottomviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kimdo.bottomviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate( layoutInflater)
        setContentView( binding.root )

        setSupportActionBar( binding.toolbar )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController



        appBarConfiguration = AppBarConfiguration( setOf( R.id.addFragment, R.id.updateFragment, R.id.deleteFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bottomNavigationView.setupWithNavController( navController )

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        fun actionFromAdd(itemId:Int): Boolean {
            return when( itemId) {
                R.id.updateFragment -> {
                    navController.navigate( AddFragmentDirections.toUpdateFromAdd() )
                    Log.d(TAG, "onOptionsItemSelected: MainActivity item.item1")
                    true
                }
                R.id.deleteFragment -> {
                    navController.navigate( AddFragmentDirections.toDeleteFromAdd() )
                    Log.d(TAG, "onOptionsItemSelected: MainActivity item.item2")
                    true
                }
                else -> false
            }
        }
        fun actionFromUpdate(itemId:Int): Boolean {
            return when( itemId) {
                R.id.addFragment -> {
                    navController.navigate( UpdateFragmentDirections.toAddFromUpdate() )
                    Log.d(TAG, "onOptionsItemSelected: MainActivity item.item1")
                    true
                }
                R.id.deleteFragment -> {
                    navController.navigate( UpdateFragmentDirections.toDeleteFromUpdate() )
                    Log.d(TAG, "onOptionsItemSelected: MainActivity item.item2")
                    true
                }
                else -> false
            }
        }
        fun actionFromDelete(itemId:Int): Boolean {
            return when( itemId) {
                R.id.addFragment -> {
                    navController.navigate( DeleteFragmentDirections.toAddFromDelete() )
                    Log.d(TAG, "onOptionsItemSelected: MainActivity item.item1")
                    true
                }
                R.id.updateFragment -> {
                    navController.navigate( DeleteFragmentDirections.toUpdateFromDelete() )
                    Log.d(TAG, "onOptionsItemSelected: MainActivity item.item2")
                    true
                }
                else -> false
            }
        }
        return when (navController.currentDestination?.id) {
            R.id.addFragment -> {
                return actionFromAdd(item.itemId)
            }
            R.id.updateFragment -> {
                return actionFromUpdate(item.itemId)
            }
            R.id.deleteFragment -> {
                return actionFromDelete(item.itemId)
            }
            else -> false
        }
    }
    companion object {
        const val TAG = "MainActivity"
    }
}