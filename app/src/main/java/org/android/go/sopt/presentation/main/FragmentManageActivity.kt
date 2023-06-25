package org.android.go.sopt.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityFragmentmanageBinding
import org.android.go.sopt.presentation.main.gallery.GalleryFragment
import org.android.go.sopt.presentation.main.home.HomeFragment
import org.android.go.sopt.presentation.main.search.MusicFragment
import org.android.go.sopt.presentation.main.search.SearchFragment

class FragmentManageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentmanageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentmanageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationBar()
        initializeDefaultFragment()
    }

    private fun setupBottomNavigationBar() {
        binding.bnvMain.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.menu_home -> HomeFragment()
                R.id.menu_search -> SearchFragment()
                R.id.menu_gallery -> GalleryFragment()
                R.id.menu_music -> MusicFragment()
                else -> null
            }
            selectedFragment?.let { changeFragment(it) }
            return@setOnItemSelectedListener selectedFragment != null
        }
    }

    private fun initializeDefaultFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            val homeFragment = HomeFragment()
            changeFragment(homeFragment)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }
}
