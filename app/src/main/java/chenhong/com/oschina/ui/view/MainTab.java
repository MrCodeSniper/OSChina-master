package chenhong.com.oschina.ui.view;

import chenhong.com.oschina.R;
import chenhong.com.oschina.fragment.DefaultFragment;
import chenhong.com.oschina.fragment.MyFragment;

/**
 *
 * 打造枚举类
 */
public enum  MainTab {
   //在类中放入枚举对象代表5个tab，用逗号隔开
    NEWS(0, R.string.main_tab_name_news,R.drawable.tab_icon_news_selector, MyFragment.class),
    TWEET(1, R.string.main_tab_name_tweet, R.drawable.tab_icon_tweet, DefaultFragment.class),
    //中间的情况特殊，不打开class
    QUICK(2,R.string.main_tab_name_quick,R.drawable.tab_icon_news_selector,null),
    EXPLORE(3, R.string.main_tab_name_explore, R.drawable.tab_icon_explore, DefaultFragment.class),
    ME(4, R.string.main_tab_name_my, R.drawable.tab_icon_me, DefaultFragment.class);


    private int id;
      private int ResName;
      private int ResIcon;
      private Class<?> clz;//传入fragment的class
    private MainTab(int id, int resName, int resIcon, Class<?> clz) {
        this.id = id;
        ResName = resName;
        ResIcon = resIcon;
        this.clz = clz;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResName() {
        return ResName;
    }

    public void setResName(int resName) {
        ResName = resName;
    }

    public int getResIcon() {
        return ResIcon;
    }

    public void setResIcon(int resIcon) {
        ResIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
