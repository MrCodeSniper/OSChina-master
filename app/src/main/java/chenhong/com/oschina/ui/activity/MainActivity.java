package chenhong.com.oschina.ui.activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import chenhong.com.oschina.R;
import chenhong.com.oschina.base.BaseActivity;
import chenhong.com.oschina.fragment.ComprehensivekFragments;
import chenhong.com.oschina.ui.view.MainTab;
import chenhong.com.oschina.ui.dialog.QuickOptionDialog;
import chenhong.com.oschina.widget.MyFragmentTabHost;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Toolbar mtoolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private MyFragmentTabHost mtabHost;
    private FrameLayout mtablayout;
    private FrameLayout mtabcontent;
    private ImageView iv_quick;
    private LinearLayout menu_item_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initTab();
//        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
//       fragmentManager.beginTransaction().replace(android.R.id.tabhost,new ComprehensivekFragments()).commitAllowingStateLoss();
        initToolbar();
    }

    private void initview() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        //1初始化tabhost
        mtabHost = (MyFragmentTabHost) findViewById(android.R.id.tabhost);
        mtabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        iv_quick = (ImageView) findViewById(R.id.quick_iv);
        iv_quick.setOnClickListener(this);
        menu_item_exit = (LinearLayout) findViewById(R.id.menu_item_exit);
        menu_item_exit.setOnClickListener(this);



    }

    private void initTab() {
        //2.使tabhost和framelayout关联
        mtabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mtabHost.getTabWidget().setShowDividers(0);
        //得到MainTab类中定义的枚举对象数组
        MainTab[] tabs = MainTab.values();
        for(int i=0;i<tabs.length;i++){
            //3.添加tab对应的fragment
            TabHost.TabSpec tab = mtabHost.newTabSpec(getString(tabs[i].getResName()));//根据R.string...的int，得到名称作为标识
            //////////////////////////////自定义选项卡/////////////////////////////////////////////
            View indicator= LayoutInflater.from(this).inflate(R.layout.tab_item,null);
            TextView tabtitle= (TextView) indicator.findViewById(R.id.tab_title);
            //得到枚举对象中的图片
            Drawable drawable=getResources().getDrawable(tabs[i].getResIcon());
            tabtitle.setText(tabs[i].getResName());
            //设置上下左右的drawable
            tabtitle.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            //----------------------特殊处理----------------------------------------//
            //如果i==2的时候
            if(i==2){
                indicator.setVisibility(View.INVISIBLE);//将这个view消失
                mtabHost.setNoTabChangedTag(getString(tabs[i].getResName()));//设置不跳到fragment的tab的名字
            }
            tab.setIndicator(indicator);//可以传入view
            mtabHost.addTab(tab, tabs[i].getClz(), null);
       //     mtabHost.addTab(tab);
        }
        mtabHost.setCurrentTab(0);
    }
    private void initToolbar() {
        mtoolbar.setTitle("开源中国");
        mtoolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mtoolbar);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,mtoolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();//同步状态，将drawerlayout和开关关联
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.quick_iv:
               showQuickOption();
               break;
           case R.id.menu_item_exit:
               changeViewMode();
               break;

       }
    }

    private void showQuickOption() {
        QuickOptionDialog dialog = new QuickOptionDialog(
                MainActivity.this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


    void changeViewMode() {
        if (currenttheme==R.style.AppTheme_night)
            ChangeToDay();
        else
            ChangeToNight();
        recreate();
    }





}
