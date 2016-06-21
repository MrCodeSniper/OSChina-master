package chenhong.com.oschina.network;

import com.github.kevinsawicki.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import chenhong.com.oschina.bean.News;
import chenhong.com.oschina.bean.NewsList;
import chenhong.com.oschina.network.api.NetWorkApi;
import chenhong.com.oschina.util.XmlUtils;

/**
 * Created by Administrator on 2016/6/20.
 */
public class NewNet extends BaseNet<List<News>> {



    @Override
    public List<News> parseData(byte[] result) {
        NewsList newsList = XmlUtils.toBean(NewsList.class, result);
        return newsList.getList();
    }


}
