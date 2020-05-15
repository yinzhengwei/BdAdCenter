package com.mayaandroid.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import com.baidu.mobads.*
import com.mayaandroid.weather.ablist.AboutActivity
import com.mayaandroid.weather.ablist.BoutActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

//    override fun onStart() {
//        super.onStart()
//
//        DemoSPUtils.list.forEach {
//            it.finish()
//        }
//
//        if (isFirst) {
//            try {
//                btn_a.postDelayed({
//                    //点击
//                    Runtime.getRuntime().exec("input tap " + 360 + " " + 320)
//                }, 2000)
//                btn_a.postDelayed({
//                    //点击
//                    Runtime.getRuntime().exec("input tap " + 300 + " " + 640)
//                }, 7000)
//                btn_a.postDelayed({
//                    //点击
//                    Runtime.getRuntime().exec("input tap " + 450 + " " + 500)
//                }, 12000)
//                btn_a.postDelayed({
//                    //滑动
//                    Runtime.getRuntime()
//                        .exec("input swipe " + 200 + " " + 1000 + " " + 200 + " " + 200)
//                }, 32000)
//                btn_a.postDelayed({
//                    //滑动
//                    Runtime.getRuntime()
//                        .exec("input swipe " + 200 + " " + 1000 + " " + 200 + " " + 200)
//                }, 33000)
////                btn_a.postDelayed({
////                    //返回
////                    Runtime.getRuntime().exec("input keyevent 4")
////                    Runtime.getRuntime().exec("input keyevent 4")
////                }, 34000)
////                btn_a.postDelayed({
////                    //点击
////                    Runtime.getRuntime().exec("input tap " + 450 + " " + 330)
////                }, 34500)
////                btn_a.postDelayed({
////                    //点击
////                    Runtime.getRuntime().exec("input tap " + 550 + " " + 920)
////                }, 40000)
////                btn_a.postDelayed({
////                    //滑动
////                    Runtime.getRuntime()
////                        .exec("input swipe " + 200 + " " + 1000 + " " + 200 + " " + 200)
////                }, 58000)
////                btn_a.postDelayed({
////                    //滑动
////                    Runtime.getRuntime()
////                        .exec("input swipe " + 200 + " " + 1000 + " " + 200 + " " + 200)
////                }, 59000)
//                btn_a.postDelayed({
//
//                    isFirst = false
//                    //返回
//                    Runtime.getRuntime().exec("input keyevent 4")
//                    //返回
//                    Runtime.getRuntime().exec("input keyevent 4")
//                    Runtime.getRuntime().exec("input keyevent 4")
//                }, 45000)
//                btn_a.postDelayed({
//                    DemoSPUtils.list.forEach {
//                        it.finish()
//                    }
//                    DemoSPUtils.list.clear()
//                }, 45100)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        } else {
//            try {
//                btn_a.postDelayed({
//                    //点击
//                    Runtime.getRuntime().exec("input tap " + 410 + " " + 470)
//                }, 2000)
//                btn_a.postDelayed({
//                    //点击
//                    Runtime.getRuntime().exec("input tap " + 560 + " " + 920)
//                }, 6000)
//                btn_a.postDelayed({
//                    //点击
//                    Runtime.getRuntime().exec("input tap " + 510 + " " + 440)
//                }, 12000)
//                btn_a.postDelayed({
//                    //滑动
//                    Runtime.getRuntime()
//                        .exec("input swipe " + 200 + " " + 1000 + " " + 200 + " " + 200)
//                }, 27000)
//                btn_a.postDelayed({
//                    //滑动
//                    Runtime.getRuntime()
//                        .exec("input swipe " + 200 + " " + 1000 + " " + 200 + " " + 200)
//                }, 28000)
//                btn_a.postDelayed({
//
//                    isFirst = true
//                    //返回
//                    Runtime.getRuntime().exec("input keyevent 4")
//                    //返回
//                    Runtime.getRuntime().exec("input keyevent 4")
//                    //返回
//                    Runtime.getRuntime().exec("input keyevent 4")
//                    Runtime.getRuntime().exec("input keyevent 4")
//                }, 31000)
////                btn_a.postDelayed({
////                    //返回
////                    Runtime.getRuntime().exec("input keyevent 4")
////                }, 32000)
////                btn_a.postDelayed({
////                    //返回
////                    Runtime.getRuntime().exec("input keyevent 4")
////                }, 33000)
//                btn_a.postDelayed({
////                    //返回
////                    Runtime.getRuntime().exec("input keyevent 4")
//                    DemoSPUtils.list.forEach {
//                        it.finish()
//                    }
//                    DemoSPUtils.list.clear()
//                }, 31100)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private var isFirst = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val adPlaceId = "2015351" // 重要：请填上您的广告位ID，代码位错误会导致无法请求到广告
//        // 如果开屏需要支持vr,needRequestVRAd(true)
////        SplashAd.needRequestVRAd(true);
//        // 等比缩小放大，裁剪边缘部分
////        SplashAd.setBitmapDisplayMode(BitmapDisplayMode.DISPLAY_MODE_CENTER_CROP);
//        SplashAd(this, adsParent, listener, adPlaceId, true)


        // 广告展现前先调用sdk初始化方法，可以有效缩短广告第一次展现所需时间
        BaiduManager.init(this)

        MobadsPermissionSettings.setPermissionReadDeviceID(true)
        MobadsPermissionSettings.setPermissionAppList(true)

        //initMobadsPermissions()

        btn_a.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
        btn_b.setOnClickListener {
            startActivity(Intent(this, BoutActivity::class.java))
        }

//        val listener = object : SplashAdListener {
//            override fun onAdFailed(p0: String?) {
//
//            }
//
//            override fun onAdDismissed() {
//            }
//
//            override fun onAdPresent() {
//            }
//
//            override fun onAdClick() {
//            }
//        }
        // 增加lp页面关闭回调，不需要该回调的继续使用原来接口就可以
//        val listener = object : SplashLpCloseListener {
//            override fun onLpClosed() {
//                Toast.makeText(this@MainActivity, "lp页面关闭", Toast.LENGTH_SHORT).show()
//                // 落地页关闭后关闭广告，并跳转到应用的主页
////                if (mExitAfterLp) {
////                    jumpWhenCanClick()
////                }
//            }
//
//            override fun onAdDismissed() {
//                Log.w(TAG, "onAdDismissed")
//                //jumpWhenCanClick() // 跳转至您的应用主界面
//            }
//
//            override fun onAdFailed(arg0: String) {
//                Log.w(TAG, arg0)
//                // jump()
//            }
//
//            override fun onAdPresent() {
//                Log.w(TAG, "onAdPresent")
//                // mSplashHolder.setVisibility(View.GONE)
//            }
//
//            override fun onAdClick() {
//                Log.w(TAG, "onAdClick")
//                // 设置开屏可接受点击时，该回调可用
//            }
//        }


//        val adPlaceId = "7075404" // 重要：请填上您的广告位ID，代码位错误会导致无法请求到广告
//        SplashAd(this, layout_flash, listener, adPlaceId, true)
    }

    // SP的key, 读取设备信息权限
    private val KEY_PHONE_STATE = "key_phone_state"
    // SP的key, 读取定位权限
    private val KEY_LOCATION = "key_location"
    // SP的key, 读写外部存储权限（SD卡）
    private val KEY_STORAGE = "key_storage"
    // SP的key, 读取应用列表权限
    private val KEY_APP_LIST = "key_app_list"

    /**
     * 初始化设置广告SDK的权限, 从SharedPreference中读取存储的权限状态
     */
    fun initMobadsPermissions() {
        MobadsPermissionSettings
            .setPermissionReadDeviceID(DemoSPUtils.getBoolean(this, KEY_PHONE_STATE, true))
        MobadsPermissionSettings
            .setPermissionLocation(DemoSPUtils.getBoolean(this, KEY_LOCATION, true))
        MobadsPermissionSettings
            .setPermissionStorage(DemoSPUtils.getBoolean(this, KEY_STORAGE, true))
        MobadsPermissionSettings
            .setPermissionAppList(DemoSPUtils.getBoolean(this, KEY_APP_LIST, true))
    }
}
