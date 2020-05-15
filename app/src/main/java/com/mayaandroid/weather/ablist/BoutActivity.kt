package com.mayaandroid.weather.ablist

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baidu.mobads.AdSize
import com.baidu.mobads.InterstitialAd
import com.baidu.mobads.InterstitialAdListener
import com.mayaandroid.weather.DemoSPUtils
import com.mayaandroid.weather.R
import kotlinx.android.synthetic.main.b_layout.*

class BoutActivity : AppCompatActivity(), InterstitialAdListener {

    val adPlaceId = "7075404"

    private var mInterAd: InterstitialAd? = null            // 插屏广告实例，支持单例模式
    private var mAdType = "interAd"         // 插屏广告的类型，Demo使用，避免重复创建广告实例
    private var mAdPlaceIdView: EditText? = null            // 广告位id
    private var isAdForVideo = false       // 视频插屏广告
    private var reLayoutParams: RelativeLayout.LayoutParams? = null
    private var isQianTiePianAd = true     // 前贴片广告 or 暂停页广告

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.b_layout)

        DemoSPUtils.list.add(this)

        // 视频插屏广告：初始化展示布局
        val mVideoAdLayout = RelativeLayout(this)
        reLayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        reLayoutParams?.addRule(RelativeLayout.CENTER_IN_PARENT)
        layout_adlist.addView(mVideoAdLayout, reLayoutParams)

        // 创建插屏广告实例
        createInterstitialAd()

        // 提前加载插屏广告
        loadAd()
    }

    /**
     * 创建广告实例，支持：插屏、前贴片、暂停页
     */
    private fun createInterstitialAd() {
        if (isAdForVideo) {
            if (isQianTiePianAd) {
                if ("qianTiePian" != mAdType) {
                    // 创建前贴片广告
                    mInterAd =
                        InterstitialAd(this, AdSize.InterstitialForVideoBeforePlay, adPlaceId)
                    mInterAd?.setListener(this)
                    mAdType = "qianTiePian"
                }
            } else {
                // 创建暂停页广告
                if ("zanTingYe" != mAdType) {
                    mInterAd = InterstitialAd(this, AdSize.InterstitialForVideoPausePlay, adPlaceId)
                    mInterAd?.setListener(this)
                    mAdType = "zanTingYe"
                }
            }
        } else {
            // 创建插屏广告
            if (mInterAd == null || "interAd" != mAdType) {
                mInterAd = InterstitialAd(this, adPlaceId)
                mInterAd?.setListener(this)
                mAdType = "interAd"
            }
        }
    }

    /**
     * 加载广告
     */
    private fun loadAd() {
        if (isAdForVideo) {
            reLayoutParams?.width = 600
            reLayoutParams?.height = 500
            mInterAd?.loadAdForVideoApp(600, 500)
        } else {
            mInterAd?.loadAd()
        }
    }

    /**
     * 展现广告
     */
    private fun showAd() {
        if (isAdForVideo) {
            mInterAd?.showAdInParentForVideoApp(this, layout_adlist)
        } else {
            mInterAd?.showAd(this)
        }
    }
    override fun onAdFailed(p0: String?) {
        Log.i("InterstitialAd", "onAdFailed")
    }

    override fun onAdDismissed() {
        Log.i("InterstitialAd", "onAdDismissed")
        Toast.makeText(this, "加载插屏广告", Toast.LENGTH_SHORT).show()
        if (!isAdForVideo) {
            loadAd()
        }
    }

    override fun onAdPresent() {
        Log.i("InterstitialAd", "onAdPresent")
    }

    override fun onAdClick(p0: InterstitialAd?) {
        Log.i("InterstitialAd", "onAdClick")
    }

    override fun onAdReady() {
        Log.i("InterstitialAd", "onAdReady")
        Toast.makeText(this, "展现插屏广告", Toast.LENGTH_SHORT).show()

        showAd()
    }
}
