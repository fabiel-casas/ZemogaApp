package com.casas.fabiel.zemogaapp.ui

import android.arch.lifecycle.LifecycleOwner
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.casas.fabiel.zemogaapp.R
import com.casas.fabiel.zemogaapp.ui.fragment.PostsListFragment

class MainActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.mainContainer, PostsListFragment(), "").commit()
    }
}
