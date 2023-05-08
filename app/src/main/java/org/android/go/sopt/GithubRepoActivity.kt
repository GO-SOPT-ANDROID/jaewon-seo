package org.android.go.sopt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.android.go.sopt.databinding.ActivityGithubrepoBinding
import org.android.go.sopt.home.GalleryFragment
import org.android.go.sopt.home.HomeFragment
import org.android.go.sopt.home.SearchFragment

@Suppress("DEPRECATION")
class GithubRepoActivity : AppCompatActivity() {

    lateinit var binding: ActivityGithubrepoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubrepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var currentFragment  = supportFragmentManager.findFragmentById(R.id.fcv_main)
            ?: supportFragmentManager.beginTransaction()
                .add(R.id.fcv_main, HomeFragment())
                .commit()

        setupBottomNavigationListener()
    }

    private fun scrollToTop(fragment: Fragment) {
        when (fragment) {
            is HomeFragment -> fragment.scrollToTop()
        }
    }
    private fun setupBottomNavigationListener() {
        binding.bnvMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    changeFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_search -> {
                    changeFragment(SearchFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_gallery -> {
                    changeFragment(GalleryFragment())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

}