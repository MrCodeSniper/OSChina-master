package chenhong.com.oschina.fragment;

import android.os.Bundle;

import chenhong.com.oschina.R;
import chenhong.com.oschina.adapter.FragmentAdapter;
import chenhong.com.oschina.adapter.ViewPagerInfo;
import chenhong.com.oschina.base.BasePagerFragment;

/**
 * Created by Administrator on 2016/6/16.
 */
public class MyFragment extends BasePagerFragment  {
    @Override
    protected void addpageToAdapter(FragmentAdapter adapter) {
        String[] titles = getResources().getStringArray(R.array.news_viewpage_arrays);
        for(int i=0;i<titles.length;i++){
            ViewPagerInfo viewPagerInfo=new ViewPagerInfo(titles[i],ComprehensivekFragments.class,getBundle(i));
            adapter.addpage(viewPagerInfo);
        }
    }

    private Bundle getBundle(int newType){
    Bundle bundle=new Bundle();
        bundle.putInt("key", newType);
        return bundle;
    }



}
