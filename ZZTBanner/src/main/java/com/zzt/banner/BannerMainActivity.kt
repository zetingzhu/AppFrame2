package com.zzt.banner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.zzt.banner.adapter.AdapterFragmentPager
import kotlinx.android.synthetic.main.activity_banner_main.*

class BannerMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_main)
        initData()

        setListener()
    }

    private fun initData() {
        with(vp_fragment) {
            adapter = AdapterFragmentPager(this@BannerMainActivity)
            isUserInputEnabled = false
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    vp_fragment.isUserInputEnabled = true
                    rg_tab?.check(getCheckedId(position))
                }
            })
        }
    }

    private fun getCheckedId(position: Int): Int {
        return when (position) {
            0 -> R.id.rb_add
            1 -> R.id.rb_find
            2 -> R.id.rb_others
            else -> R.id.rb_add
        }
    }

    private fun setListener() {
        rg_tab?.setOnCheckedChangeListener { _, checkedId ->
            vp_fragment.isUserInputEnabled = true
            when (checkedId) {
                R.id.rb_add -> vp_fragment.setCurrentItem(AdapterFragmentPager.PAGE_FIND, true)
                R.id.rb_find -> vp_fragment.setCurrentItem(
                    AdapterFragmentPager.PAGE_INDICATOR,
                    true
                )
                R.id.rb_others -> vp_fragment.setCurrentItem(AdapterFragmentPager.PAGE_OTHERS, true)
            }
        }
    }
}