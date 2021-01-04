package com.zzt.banner.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zzt.banner.fragment.BaseFragment
import com.zzt.banner.fragment.IndicatorFragment
import com.zzt.banner.fragment.OthersFragment
import com.zzt.banner.fragment.PageFragment

/**
 * <pre>
 * Created by zhangpan on 2019-12-05.
 * Description: MainActivity Fragment Adapter.
</pre> *
 */
class AdapterFragmentPager(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: SparseArray<BaseFragment> = SparseArray()

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment
        when (position) {
            PAGE_FIND -> {
                if (fragments.get(PAGE_FIND) == null) {
                    fragment = PageFragment.getInstance();
                    fragments.put(PAGE_FIND, fragment)
                } else {
                    fragment = fragments.get(PAGE_FIND)
                }
            }
            PAGE_INDICATOR -> {
                if (fragments.get(PAGE_INDICATOR) == null) {
                    fragment = IndicatorFragment.getInstance();
                    fragments.put(PAGE_INDICATOR, fragment)
                } else {
                    fragment = fragments.get(PAGE_INDICATOR)
                }
            }

            PAGE_OTHERS -> {
                if (fragments.get(PAGE_OTHERS) == null) {
                    fragment = OthersFragment.getInstance();
                    fragments.put(PAGE_OTHERS, fragment)
                } else {
                    fragment = fragments.get(PAGE_OTHERS)
                }
            }
            else -> {
                if (fragments.get(PAGE_FIND) == null) {
                    fragment = PageFragment.getInstance();
                    fragments.put(PAGE_FIND, fragment)
                } else {
                    fragment = fragments.get(PAGE_FIND)
                }
            }
        }
        return fragment
    }

    override fun getItemCount(): Int {
        return 3
    }

    companion object {

        const val PAGE_FIND = 0

        const val PAGE_INDICATOR = 1

        const val PAGE_OTHERS = 2

    }

}
