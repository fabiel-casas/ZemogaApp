package com.casas.fabiel.zemogaapp.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.casas.fabiel.zemogaapp.ui.fragment.FavoritePostsFragment
import com.casas.fabiel.zemogaapp.ui.fragment.PostsListFragment

class PostsTabsAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if(position == 0) {
            PostsListFragment()
        } else {
            FavoritePostsFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            "ALL"
        } else {
            "FAVORITES"
        }
    }

}