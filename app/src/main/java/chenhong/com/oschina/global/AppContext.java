package chenhong.com.oschina.global;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.LayoutInflater;

/**
 * Created by Administrator on 2016/6/15.
 */
public class AppContext extends Application {

    private static Context mcontext;
    private static Handler mhandler;
    private static int mTid;
    @Override
    public void onCreate() {
        super.onCreate();
        //经常用到的
        mcontext = getApplicationContext();
        mhandler = new Handler();
        //线程的id
        mTid = android.os.Process.myTid();//当前线程id，此处是主线程id
    }

    public static Context getmcontext() {
        return mcontext;
    }

    public static Handler getmhandler() {
        return mhandler;
    }

    public static int getmTid() {
        return mTid;
    }

}
