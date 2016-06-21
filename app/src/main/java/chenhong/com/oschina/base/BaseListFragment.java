package chenhong.com.oschina.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.ButterKnife;

import butterknife.InjectView;
import chenhong.com.oschina.R;

/**
 * Created by Administrator on 2016/6/14.
 */
public abstract class BaseListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,AbsListView.OnScrollListener,AdapterView.OnItemClickListener{
    @InjectView(R.id.lv_refresh)
     protected ListView lv_refresh;
    @InjectView(R.id.swipefreshlayout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(getLayoutID(),container,false);
        return view;
    }

    private int getLayoutID() {
        return R.layout.refresh_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        initview(view);

    }

    private void initview(View view) {
      swipeRefreshLayout.setOnRefreshListener(this);
      lv_refresh.setOnScrollListener(this);
        lv_refresh.setOnItemClickListener(this);

    }


}
