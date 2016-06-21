package chenhong.com.oschina.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chenhong.com.oschina.R;
import chenhong.com.oschina.bean.News;
import chenhong.com.oschina.util.StringUtil;
import chenhong.com.oschina.util.StringUtils;

/**
 * Created by Administrator on 2016/6/14.
 */
public class ListViewAdapter extends BaseAdapter {
  private Context context;
    private List<News> list;

    public ListViewAdapter(Context context,List<News> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public News getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context,R.layout.list_cell_news,null);
        }
        ViewHolder viewHolder=ViewHolder.getHolder(convertView);
        viewHolder.tv_title.setText(getItem(position).getTitle());
        viewHolder.tv_description.setText(getItem(position).getBody());
        viewHolder.tv_source.setText(getItem(position).getAuthor());
        viewHolder.tv_time.setText(StringUtils.friendly_time(getItem(position).getPubDate()));
        viewHolder.tv_comment_count.setText(getItem(position).getCommentCount()+"");
        if(StringUtils.isToday(getItem(position).getPubDate())){
            viewHolder.iv_tip.setVisibility(View.VISIBLE);
        }else {
            viewHolder.iv_tip.setVisibility(View.GONE);
        }
        return convertView;

    }

    static class ViewHolder{
        TextView tv_title,tv_description,tv_source,tv_time,tv_comment_count;
        ImageView iv_link,iv_tip;

        public ViewHolder(View convertview){
            tv_title= (TextView) convertview.findViewById(R.id.tv_title);
            tv_description= (TextView) convertview.findViewById(R.id.tv_description);
            tv_source =(TextView) convertview.findViewById(R.id.tv_source);
            tv_time=(TextView) convertview.findViewById(R.id.tv_time);
            tv_comment_count=(TextView) convertview.findViewById(R.id.tv_comment_count);
            iv_link= (ImageView) convertview.findViewById(R.id.iv_link);
            iv_tip= (ImageView) convertview.findViewById(R.id.iv_tip);
        }
        public  static ViewHolder getHolder(View convertview){
            ViewHolder viewHolder= (ViewHolder) convertview.getTag();
            if(viewHolder==null){
                viewHolder=new ViewHolder(convertview);
                convertview.setTag(viewHolder);
            }
            return  viewHolder;
        }
    }



}
