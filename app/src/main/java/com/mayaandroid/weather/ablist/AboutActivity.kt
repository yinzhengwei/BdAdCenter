package com.mayaandroid.weather.ablist

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baidu.mobads.*
import com.mayaandroid.weather.DemoSPUtils.list
import com.mayaandroid.weather.R
import kotlinx.android.synthetic.main.a_layout.*
import org.json.JSONObject

class AboutActivity : AppCompatActivity() {

    val TAG = "AboutActivity"
    private val AD_PLACE_ID_20_3 = "7073582"
    //    private val AD_PLACE_ID_20_3 = "2015351"
    private var mNoDataView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_layout)

        list.add(this)

        mNoDataView = layoutInflater.inflate(R.layout.no_ad_view, null)

        // 设置'广告着陆页'动作栏的颜色主题
        // 目前开放了七大主题：黑色、蓝色、咖啡色、绿色、藏青色、红色、白色(默认) 主题
        AppActivity.setActionBarColorTheme(AppActivity.ActionBarColorTheme.ACTION_BAR_BLUE_THEME)

        // 重要：请填上您的广告位ID，代码位错误会导致无法请求到广告
        // 创建广告View，并添加至接界面布局中

        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
//        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
//        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
//        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
//        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
//        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
//        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
//        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
//        bindBannerView(layout_adlist, AD_PLACE_ID_20_3, 20, 3)
    }

    /**
     * 创建横幅广告的View，并添加至接界面布局中
     * 注意：只有将AdView添加到布局中后，才会有广告返回
     */
    private fun bindBannerView(
        yourOriginalLayout: LinearLayout,
        adPlaceId: String, scaleWidth: Int, scaleHeight: Int
    ) {
        val adView = AdView(this, adPlaceId)
        // 设置监听器
        adView.setListener(object : AdViewListener {
            override fun onAdSwitch() {
                Log.w(TAG, "onAdSwitch")
            }

            override fun onAdShow(info: JSONObject) {
                // 广告已经渲染出来
                Log.w(TAG, "onAdShow $info")
            }

            override fun onAdReady(adView: AdView) {
                // 资源已经缓存完毕，还没有渲染出来
                Log.w(TAG, "onAdReady $adView")
            }

            override fun onAdFailed(reason: String) {
                Log.w("", "onAdFailed $reason")
                val rllp = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                rllp.addRule(RelativeLayout.CENTER_IN_PARENT)
                yourOriginalLayout.addView(mNoDataView, rllp)
            }

            override fun onAdClick(info: JSONObject) {
                Log.w(TAG, "onAdClick $info")
            }

            override fun onAdClose(arg0: JSONObject) {
                Log.w(TAG, "onAdClose")
            }
        })

        val dm = DisplayMetrics()
        (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(dm)
        val winW = dm.widthPixels
        val winH = dm.heightPixels
        val width = Math.min(winW, winH)
        val height = width * scaleHeight / scaleWidth
        // 将adView添加到父控件中(注：该父控件不一定为您的根控件，只要该控件能通过addView能添加广告视图即可)
        val rllp = RelativeLayout.LayoutParams(width, height)
        rllp.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        adView.setPadding(0, 20, 0, 0)
        yourOriginalLayout.addView(adView, rllp)
    }
}
