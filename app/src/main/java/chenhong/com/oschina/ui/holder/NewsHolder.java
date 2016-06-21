package chenhong.com.oschina.ui.holder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import chenhong.com.oschina.R;
import chenhong.com.oschina.bean.News;
import chenhong.com.oschina.util.StringUtils;
import chenhong.com.oschina.util.UIUtils;

/**
 * Created by Administrator on 2016/6/20.
 */
public class NewsHolder extends BaseHolder<News> {

    TextView tv_title,tv_description,tv_source,tv_time,tv_comment_count;
    ImageView iv_link,iv_tip;

    @Override
    public View initview() {
        View convertview= UIUtils.inflate(R.layout.list_cell_news);
        tv_title= (TextView) convertview.findViewById(R.id.tv_title);
        tv_description= (TextView) convertview.findViewById(R.id.tv_description);
        tv_source =(TextView) convertview.findViewById(R.id.tv_source);
        tv_time=(TextView) convertview.findViewById(R.id.tv_time);
        tv_comment_count=(TextView) convertview.findViewById(R.id.tv_comment_count);
        iv_link= (ImageView) convertview.findViewById(R.id.iv_link);
        iv_tip= (ImageView) convertview.findViewById(R.id.iv_tip);
        return convertview;
    }

    @Override
    public void refreashView(News data) {
       tv_title.setText(data.getTitle());
       tv_description.setText(data.getBody());
        Log.d("tag",data.getBody());
       tv_source.setText(data.getAuthor());
        tv_time.setText(StringUtils.friendly_time(data.getPubDate()));
       tv_comment_count.setText(data.getCommentCount()+"");
        if(StringUtils.isToday(data.getPubDate())){
            iv_tip.setVisibility(View.VISIBLE);
        }else {
            iv_tip.setVisibility(View.GONE);
        }
    }

}
