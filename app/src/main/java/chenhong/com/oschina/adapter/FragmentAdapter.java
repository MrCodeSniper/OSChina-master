package chenhong.com.oschina.adapter;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    //需要传入的参数
    private ArrayList<ViewPagerInfo> fragments;
    private ArrayList<String> tabTitles;
    private Context context;

//    private String tabTitles[] = new String[]{"资讯","热点","博客","推荐"};
//    final int PAGE_COUNT = 4;

    public FragmentAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;
        tabTitles=new ArrayList<String>();
        fragments=new ArrayList<ViewPagerInfo>();
    }

    /**
     * 加入对应的title和fragment
     */
    public void addpage(ViewPagerInfo viewPagerInfo){
        tabTitles.add(viewPagerInfo.title);
        fragments.add(viewPagerInfo);
//        fragments.add(new FragmentInfo(clazz,bundle));
    }









    public Fragment getItem(int position) {
        ViewPagerInfo fragment=fragments.get(position);
        return Fragment.instantiate(context, fragment.clss.getName(), fragment.args);
//        return fragments.get(position);
    }


    public int getCount() {
        return tabTitles.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }



}