package com.soha.webkeyztask.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.si.reach.bases.BaseActivity
import com.soha.webkeyztask.R
import com.soha.webkeyztask.databinding.ActivityMainBinding
import com.soha.webkeyztask.ui.news.NewsFragment
import com.soha.webkeyztask.utils.FragmentNavigation

class MainActivity : BaseActivity(),FragmentNavigation {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private val fragNavController by lazy { FragNavController(supportFragmentManager, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this@MainActivity
        setupFragmentNavController(savedInstanceState)
    }

    private fun setupFragmentNavController(savedInstanceState : Bundle?){
        val fragments: List<Fragment> = listOf(NewsFragment())
        fragNavController.apply {
            createEager = true
            fragNavController.rootFragments = fragments
            defaultTransactionOptions = FragNavTransactionOptions.newBuilder().build()
            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH
        }
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragNavController.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (fragNavController.isRootFragment.not())
            fragNavController.popFragment()
        else super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    override fun pushFragment(fragment: Fragment, sharedElementList: List<Pair<View, String>>?) {
        val options = FragNavTransactionOptions.newBuilder()
        sharedElementList?.let {
            it.forEach { pair ->
                options.addSharedElement(pair)
            }
        }
        fragNavController.pushFragment(fragment, options.build())
    }
}