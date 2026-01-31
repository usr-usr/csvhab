package com.example.hab.ui.base

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
//
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
//
import kotlinx.coroutines.flow.StateFlow
//
import com.example.hab.R
import com.example.hab.databinding.ActivityBaseBinding
import com.example.hab.ui.state.UiState
//
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import kotlinx.coroutines.launch

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    // base
    private lateinit var baseBinding: ActivityBaseBinding

    // child
    protected lateinit var binding: VB
        private set
    protected abstract fun createViewBinding(): VB
    protected abstract fun setupView(savedInstanceState: Bundle?)
    protected abstract fun getAdUnitId(): String
    protected abstract fun getTAG(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 共通レイアウト
        baseBinding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)

        // Toolbar 設定
        setSupportActionBar(baseBinding.toolbar.toolbar)

        // 各 Activity のレイアウトを差し込む
        binding = createViewBinding()
        baseBinding.contentContainer.addView(binding.root)

        setupView(savedInstanceState)
        window.decorView.post {
            setupAd()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onToolbarMenuSelected(item.itemId)
        return true
    }

    protected fun setupAd() {
        val adUnitId = getAdUnitId() ?: return

        val adContainer = baseBinding.adContainer
        adContainer.removeAllViews()

        val adView = AdView(this).apply {
            this.adUnitId = adUnitId
            this.setAdSize(AdSize.BANNER)
        }

        adContainer.addView(adView)

        adView.loadAd(AdRequest.Builder().build())
    }

    protected open fun onToolbarMenuSelected(menuId: Int) {  }

    //==============================================================================================
    // Utility
    //==============================================================================================
    protected fun showLog(log: String){
        Log.d(getTAG(), log)
    }
    protected fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    protected fun navigateTo(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }
}
