package com.github.baby.owspace.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baby.owspace.R;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.util.TimeUtil;

import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/4
 * owspace
 */
public class DailyItemFragment extends Fragment {
    @Bind(R.id.month_tv)
    TextView monthTv;
    @Bind(R.id.year_tv)
    TextView yearTv;
    @Bind(R.id.date_rl)
    RelativeLayout dateRl;
    @Bind(R.id.calendar_iv)
    ImageView calendarIv;

    public static Fragment getInstance(Item item){
        Fragment fragment = new DailyItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("item",item);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_daily, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Item item = getArguments().getParcelable("item");
        Glide.with(getActivity()).load(item.getThumbnail()).centerCrop().into(calendarIv);
        String[] arrayOfString = TimeUtil.getCalendarShowTime(item.getUpdate_time());
        if ((arrayOfString != null) && (arrayOfString.length == 3))
        {
            monthTv.setText(arrayOfString[1] + " , " + arrayOfString[2]);
            yearTv.setText(arrayOfString[0]);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
