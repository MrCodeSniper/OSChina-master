package chenhong.com.oschina.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import chenhong.com.oschina.R;
import chenhong.com.oschina.adapter.FragmentAdapter;

/**
 * 对应tab底栏的fragment
 * Created by Administrator on 2016/6/16.
 */
public  abstract class BasePagerFragment extends Fragment {
    @InjectView(R.id.viewPager)
    protected ViewPager mviewPager;
    @InjectView(R.id.tabs)
    protected TabLayout mtabLayout;
    private FragmentAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.new_item,null);
        ButterKnife.inject(this,view);
        return view;
    }

    //oncreatview之后
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view设置好在设置数据
        //1.设置适配器
        //因为fragment中嵌套fragment，那么应该用getChildFragmentManager()
        adapter = new FragmentAdapter(getChildFragmentManager(),getActivity());
        //让子类给adapter添加page
        addpageToAdapter(adapter);

        mviewPager.setAdapter(adapter);
        //绑定tablayout
        mtabLayout.setupWithViewPager(mviewPager);

        setScreenPageLimit(mviewPager);
    }

    private void setScreenPageLimit(ViewPager mviewPager) {
        //缓存viewpager的页数
        mviewPager.setOffscreenPageLimit(mviewPager.getAdapter().getCount()-1);
    }

    protected  abstract void addpageToAdapter(FragmentAdapter adapter);




}
