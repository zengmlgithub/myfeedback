package com.sanyedu.myfeedback.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanyedu.myfeedback.R;

import java.util.List;

public class MainAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> fragmentList;
    private List<String> list_Title;
    private int[] myDrawables;
    private TabLayout tabLayout;
    public MainAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList, List<String> list_Title, int[] myDrawables, TabLayout tableLayout) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
        this.myDrawables = myDrawables;
        this.tabLayout = tableLayout;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
    @Override
    public int getCount() {
        return list_Title.size();
    }
    /**
     * //此方法用来显示tab上的名字
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }

    /**
     * 自定义方法，提供自定义Tab
     *
     * @param position 位置
     * @return 返回Tab的View
     */
    public View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.tab_custom, null);
        TextView textView = (TextView) v.findViewById(R.id.tv_title);
        ImageView imageView = (ImageView) v.findViewById(R.id.iv_icon);
        textView.setText(list_Title.get(position));
        imageView.setImageResource(myDrawables[position]);
        //添加一行，设置颜色
        textView.setTextColor(tabLayout.getTabTextColors());//
        return v;
    }

}
