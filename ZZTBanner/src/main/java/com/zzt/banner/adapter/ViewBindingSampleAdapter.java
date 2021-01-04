package com.zzt.banner.adapter;

import android.widget.ImageView;

import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;
import com.zzt.banner.R;

/**
 * @author DBoy
 * @date 2020/12/11
 * Class 描述 : 使用ViewBinding示例
 */
public class ViewBindingSampleAdapter extends BaseBannerAdapter<Integer> {

    private final int mRoundCorner;

    public ViewBindingSampleAdapter(int roundCorner) {
        mRoundCorner = roundCorner;
    }

    @Override
    protected void bindData(BaseViewHolder<Integer> holder, Integer data, int position, int pageSize) {
        ImageView imageView = holder.findViewById(R.id.banner_image);
        imageView.setImageResource(data);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_slide_mode;
    }
}

