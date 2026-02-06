package com.example.myhealth

import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        // Handle system insets (status bar & navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.DashboardLayout)
        ) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // 🔥 Disable ALL Material tinting & indicators
        bottomNav.itemIconTintList = null
        bottomNav.itemTextColor = null
        bottomNav.isItemActiveIndicatorEnabled = false

        // Default screen
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
            bottomNav.selectedItemId = R.id.nav_home
            updateBottomNavSelection(bottomNav, R.id.nav_home)
        }

        bottomNav.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_medicine -> loadFragment(MedicinesFragment())
                R.id.nav_health -> loadFragment(HealthFragment())
                R.id.nav_appointment -> loadFragment(AppointmentsFragment())
                R.id.nav_menu -> loadFragment(MenuFragment())
            }

            updateBottomNavSelection(bottomNav, item.itemId)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    /**
     * Scales selected icon and adds background shadow
     */
    private fun updateBottomNavSelection(
        bottomNav: BottomNavigationView,
        selectedItemId: Int
    ) {
        val menuView = bottomNav.getChildAt(0) as? ViewGroup ?: return

        for (i in 0 until menuView.childCount) {
            val itemView = menuView.getChildAt(i)
            val isSelected = bottomNav.menu.getItem(i).itemId == selectedItemId

            itemView.animate()
                .scaleX(if (isSelected) 1.5f else 1.25f)
                .scaleY(if (isSelected) 1.5f else 1.25f)
                .setDuration(200)
                .setInterpolator(DecelerateInterpolator())
                .start()

            itemView.background =
                if (isSelected) {
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.nav_item_selected_bg
                    )
                } else {
                    null
                }

            // Optional elevation for soft shadow (API 21+)
            itemView.elevation = if (isSelected) 12f else 0f
        }
    }
}
