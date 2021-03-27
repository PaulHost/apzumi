package com.apzumi.challenge

import android.os.Bundle
import com.apzumi.challenge.ui.RepositoriesFragment
import com.apzumi.challenge.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override val container: Int
        get() = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState ?: run {
            startFragment(RepositoriesFragment.newInstance())
        }
    }
}