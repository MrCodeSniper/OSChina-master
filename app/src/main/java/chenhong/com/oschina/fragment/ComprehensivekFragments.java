package chenhong.com.oschina.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import chenhong.com.oschina.R;
import chenhong.com.oschina.adapter.MyBaseListAdapter;
import chenhong.com.oschina.base.BaseFragment;
import chenhong.com.oschina.bean.News;
import chenhong.com.oschina.network.NewNet;
import chenhong.com.oschina.ui.holder.BaseHolder;
import chenhong.com.oschina.ui.holder.NewsHolder;
import chenhong.com.oschina.ui.view.LoadingPage;
import chenhong.com.oschina.util.UIUtils;

/**
 * Created by Administrator on 2016/6/13.
 */
public class ComprehensivekFragments extends BaseFragment {

    public static final String URL_NEWS="oschina/list/news/page";

    private int page;
    private SwipeRefreshLayout srl;
    private ListView lv_refresh;
    private String[] adapterData;
    private List<News> list;
    private List<News> data;


    private View rootView;// 缓存Fragment view

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Bundle bundle=getArguments();
          if(bundle!=null){
              page = bundle.getInt("key",-1);
          }
    }



    @Override
    public LoadingPage.ResultState onLoadingData() {
        data=new ArrayList<News>();
        String path=URL_NEWS+page+".xml";
        NewNet newNet=new NewNet();
        data = newNet.getData(path);
         return check(data);
}

    @Override
    public View onCreatSuccessView() {
        //缓存tab
        if (rootView == null)
        {
            rootView = UIUtils.inflate(R.layout.refresh_view);
            lv_refresh = (ListView) rootView.findViewById(R.id.lv_refresh);
            lv_refresh.setAdapter(new NewAdapter(data));
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }




    class NewAdapter extends MyBaseListAdapter<News>{

        public NewAdapter(List<News> data) {
            super(data);
        }

        @Override
        public BaseHolder<News> getHolder(int position) {
            return new NewsHolder();
        }

    }

}
