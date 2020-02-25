package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.news.fragments.ArticleLikeFragment
import com.example.news.fragments.ArticlesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_home -> selectedFragment = ArticlesFragment()
            R.id.navigation_like -> selectedFragment = ArticleLikeFragment()
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            selectedFragment!!).commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val articleFragment = ArticlesFragment()
        val bottomNav = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, articleFragment)
            addToBackStack(null)
        }.commit()

    }

}
