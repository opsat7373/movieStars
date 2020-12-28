package com.opsat7373.moviestars.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.opsat7373.moviestars.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.addOnBackStackChangedListener(
            object : FragmentManager.OnBackStackChangedListener {
                override fun onBackStackChanged() {
                    for (i in 0 until supportFragmentManager.backStackEntryCount) println(
                        supportFragmentManager.getBackStackEntryAt(i).name
                    )
                }
            })
        setContentView(R.layout.activity_main)
    }
}