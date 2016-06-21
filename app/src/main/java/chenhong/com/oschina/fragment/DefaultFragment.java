package chenhong.com.oschina.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

import chenhong.com.oschina.bean.News;
import chenhong.com.oschina.bean.NewsList;
import chenhong.com.oschina.network.api.NetWorkApi;
import chenhong.com.oschina.util.XmlUtils;


/**
 * Created by Administrator on 2016/6/17.
 */
public class DefaultFragment extends Fragment {

    private TextView view;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
           byte[] result= data.getByteArray("value");
            // TODO
            // UI界面的更新等相关操作
            NewsList newsList = XmlUtils.toBean(NewsList.class,result);
            List<News> list = newsList.getList();
            view.setText(list.get(0).getAuthor());
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new TextView(getContext());
//        new Thread(networkTask).start();
        view.setText("fragment");
        return view;
    }




    public String getData() {
        int catlog = 1;
        int page = 0;
        return null;

    }







    Runnable networkTask =new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            Bundle data = new Bundle();
//            data.putByteArray("value", NetWorkApi.getNewsList(1,0));
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };



}