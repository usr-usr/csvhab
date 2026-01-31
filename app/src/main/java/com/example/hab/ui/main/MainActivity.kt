package com.example.hab.ui.main

import android.os.Bundle
//
import com.example.hab.R
import com.example.hab.ui.base.BaseActivity
import com.example.hab.databinding.ActivityMainBinding
import com.example.hab.ui.entry.RecordActivity
import com.example.hab.ui.setting.SettingActivity


class MainActivity : BaseActivity<ActivityMainBinding>() {

    //==============================================================================================
    // override
    //==============================================================================================
    override fun getTAG(): String {
        return "MainActivity"
    }
    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
    override fun setupView(savedInstanceState: Bundle?) {
        // 初期化処理
    }
    override fun onToolbarMenuSelected(menuId: Int) {
        when (menuId) {
            R.id.menu_home -> navigateTo(MainActivity::class.java)
            R.id.menu_entry -> navigateTo(RecordActivity::class.java)
            R.id.menu_setting -> navigateTo(SettingActivity::class.java)
        }
    }
    override fun getAdUnitId(): String {
        return "ca-app-pub-3940256099942544/6300978111"
    }
}