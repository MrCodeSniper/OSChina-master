package chenhong.com.oschina.base;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;

import chenhong.com.oschina.R;
import chenhong.com.oschina.global.AppContext;

/**
 * Created by Administrator on 2016/6/13.
 */
public class BaseActivity extends ActionBarActivity{


    protected static int currenttheme=R.style.AppTheme_day;
    private WindowManager mWindowManager = null;
    private View mNightView = null;
    private WindowManager.LayoutParams mNightViewParam;
    private boolean mIsAddedView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (currenttheme!=R.style.AppTheme_day)
            setTheme(R.style.AppTheme_night);
        else
            setTheme(R.style.AppTheme_day);
        super.onCreate(savedInstanceState);
        mIsAddedView = false;
        if (currenttheme!=R.style.AppTheme_day) {
            initNightView();
            mNightView.setBackgroundResource(R.color.night_mask);
        }
    }
    @Override
    protected void onDestroy() {
        if (mIsAddedView) {
//            mBaseApp = null;
            mWindowManager.removeViewImmediate(mNightView);
            mWindowManager = null;
            mNightView = null;
        }
        super.onDestroy();
    }
    public void ChangeToDay() {
        currenttheme=R.style.AppTheme_day;
        mNightView.setBackgroundResource(android.R.color.transparent);
    }
    public void ChangeToNight() {
        currenttheme=R.style.AppTheme_night;
        initNightView();
        mNightView.setBackgroundResource(R.color.night_mask);
    }
    /**
     * wait a time until the onresume finish
     */
    public void recreateOnResume() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                recreate();
            }
        }, 100);
    }
    private void initNightView() {
        if (mIsAddedView == true)
            return;
        mNightViewParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mNightView = new View(this);
        mWindowManager.addView(mNightView, mNightViewParam);
        mIsAddedView = true;
    }












}
