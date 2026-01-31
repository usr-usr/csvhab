package com.example.hab.ui.setting

import android.os.Bundle
//
import com.example.hab.ui.base.BaseActivity
import com.example.hab.databinding.ActivitySettingBinding
import com.example.hab.ui.setting.category_rule.CategoryAssignmentRuleActivity

class SettingActivity : BaseActivity<ActivitySettingBinding>() {

    //==============================================================================================
    // override
    //==============================================================================================
    override fun getTAG(): String {
        return "SettingActivity"
    }
    override fun createViewBinding(): ActivitySettingBinding {
        return ActivitySettingBinding.inflate(layoutInflater)
    }
    override fun setupView(savedInstanceState: Bundle?) {
        // listener
        binding.autoCategorizeItemSettingButton.setOnClickListener {
            navigateTo(CategoryAssignmentRuleActivity::class.java)
        }
    }
    override fun getAdUnitId(): String {
        return "ca-app-pub-3940256099942544/6300978111"
    }

    //==============================================================================================
    // override
    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}
