package com.mayaandroid.weather

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.baidu.mobads.SplashAd
import com.baidu.mobads.SplashLpCloseListener
import kotlinx.android.synthetic.main.splash.*

/**
 * Create by yinzhengwei on 2020-05-15
 * @Function
 */
class FlashActivity : Activity() {

    /**
     * 当设置开屏可点击时，需要等待跳转页面关闭后，再切换至您的主窗口。故此时需要增加canJumpImmediately判断。 另外，点击开屏还需要在onResume中
     * 调用jumpWhenCanClick接口。
     */
    private var canJumpImmediately = false
    // 控制开屏广告在落地页关闭后自动关闭，并进入到媒体的应用主页
    private var mExitAfterLp: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        // 根据工信部的规定，不再默认申请权限，而是主动弹框由用户授权使用
        // 如果是Android6.0以下的机器, 或者targetSDKVersion < 23，默认在安装时获得了所有权限，可以直接调用SDK
        fetchSplashAD()
    }

    private fun fetchSplashAD() {
        // 默认请求https广告，若需要请求http广告，请设置AdSettings.setSupportHttps为false
        // AdSettings.setSupportHttps(false);
        // 设置视频广告最大缓存占用空间(15MB~100MB),默认30MB,单位MB
        // SplashAd.setMaxVideoCacheCapacityMb(30);
        // adUnitContainer
//        val adsParent = this.findViewById(R.id.adsRl) as RelativeLayout

        // 增加lp页面关闭回调，不需要该回调的继续使用原来接口就可以
        val listener = object : SplashLpCloseListener {
            override fun onLpClosed() {
                Toast.makeText(this@FlashActivity, "lp页面关闭", Toast.LENGTH_SHORT).show()
                // 落地页关闭后关闭广告，并跳转到应用的主页
                if (mExitAfterLp) {
                    jumpWhenCanClick()
                }
            }

            override fun onAdDismissed() {
                Log.i("RSplashActivity", "onAdDismissed")
                jumpWhenCanClick() // 跳转至您的应用主界面
            }

            override fun onAdFailed(arg0: String) {
                Log.i("RSplashActivity", arg0)
                jump()
                Toast.makeText(this@FlashActivity, "加载失败", Toast.LENGTH_SHORT).show()
            }

            override fun onAdPresent() {
                Log.i("RSplashActivity", "onAdPresent")
                splash_holder.setVisibility(View.GONE)
            }

            override fun onAdClick() {
                Log.i("RSplashActivity", "onAdClick")
                // 设置开屏可接受点击时，该回调可用
            }
        }
        // 不需要使用lp关闭之后回调方法的，可以继续使用该接口
        //        SplashAdListener listener = new SplashAdListener() {
        //            @Override
        //            public void onAdDismissed() {
        //                Log.i("RSplashActivity", "onAdDismissed");
        //                jumpWhenCanClick(); // 跳转至您的应用主界面
        //            }
        //
        //            @Override
        //            public void onAdFailed(String arg0) {
        //                Log.i("RSplashActivity", arg0);
        //                jump();
        //            }
        //
        //            @Override
        //            public void onAdPresent() {
        //                Log.i("RSplashActivity", "onAdPresent");
        //            }
        //
        //            @Override
        //            public void onAdClick() {
        //                Log.i("RSplashActivity", "onAdClick");
        //                // 设置开屏可接受点击时，该回调可用
        //            }
        //        };

        //        AdSettings.setSupportHttps(false);
        val adPlaceId = "7075421" // 重要：请填上您的广告位ID，代码位错误会导致无法请求到广告
        // 如果开屏需要支持vr,needRequestVRAd(true)
        //        SplashAd.needRequestVRAd(true);
        // 等比缩小放大，裁剪边缘部分
        //        SplashAd.setBitmapDisplayMode(BitmapDisplayMode.DISPLAY_MODE_CENTER_CROP);
        SplashAd(this, adsRl, listener, adPlaceId, true)
    }

    private fun jumpWhenCanClick() {
        Log.d("RSplashActivity", "this.hasWindowFocus():" + this.hasWindowFocus())
        if (canJumpImmediately) {
            this.startActivity(Intent(this@FlashActivity, MainActivity::class.java))
            this.finish()
        } else {
            canJumpImmediately = true
        }
    }

    /**
     * 不可点击的开屏，使用该jump方法，而不是用jumpWhenCanClick
     */
    private fun jump() {
        this.startActivity(Intent(this@FlashActivity, MainActivity::class.java))
        this.finish()
    }

    override fun onPause() {
        super.onPause()
        canJumpImmediately = false
    }

    override fun onResume() {
        super.onResume()
        if (canJumpImmediately) {
            jumpWhenCanClick()
        }
        canJumpImmediately = true
    }

}